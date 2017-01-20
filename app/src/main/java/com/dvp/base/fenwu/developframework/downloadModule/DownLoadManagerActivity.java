package com.dvp.base.fenwu.developframework.downloadModule;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dvp.base.fenwu.developframework.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DownLoadManagerActivity extends AppCompatActivity
{

    @Bind(R.id.progressBar2)
    ProgressBar progressBar2;
    @Bind(R.id.textView4)
    TextView textView4;
    private DownloadManager downloadManager;

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_manager);
        ButterKnife.bind(this);

        init();
    }

    /*
    DownloadManager.Request一些常用方法：

        setDestinationInExternalFilesDir 设置文件下载路径
        allowScanningByMediaScanner() 表示允许MediaScanner扫描到这个文件夹，默认不允许。
        setTitle()设置下载中通知栏提示的标题
        setDescription()设置下载中通知栏提示的介绍。
        setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)表示
        下载进行中和下载完成的通知栏是否显示。
        默认只显示下载中通知。VISIBILITY_VISIBLE_NOTIFY_COMPLETED表示下载完成后显示通知栏提示。
        VISIBILITY_HIDDEN表示不显示任何通知栏提示，这个需要在AndroidMainfest中添加权限android.permission.DOWNLOAD_WITHOUT_NOTIFICATION.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI) 表示下载允许的网络类型，默认在任何网络下都允许下载。有NETWORK_MOBILE、NETWORK_WIFI、NETWORK_BLUETOOTH三种及其组合可供选择。如果只允许wifi下载，而当前网络为3g，则下载会等待。
        request.setAllowedOverRoaming(boolean allow)移动网络情况下是否允许漫游。
        request.setMimeType() 设置下载文件的mineType。因为下载管理Ui中点击某个已下载完成文件及下载完成点击通知栏提示都会根据mimeType去打开文件。
        request.addRequestHeader(String header, String value)添加请求下载的网络链接的http头，比如User-Agent，gzip压缩等
    * */
    private void init()
    {
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        //文件下载地址
        String url = "http://124.205.69.162/files/B051000005218361/file.anruan.com/soft/5/meituan_6.9.2.apk";
        //创建一个Request对象
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置下载文件路径
        request.setDestinationInExternalPublicDir("dfwpp", "bbb.apk");

        request.setTitle("美丽加下载");
        request.setDescription("途牛app的下载，哈哈哈哈哈哈哈哈哈哈哈");
        //设置下载完成时候打开类型
        request.setMimeType("application/vnd.android.package-archive");
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(true);
        //开始下载
        final long downloadId = downloadManager.enqueue(request);

        myHandler = new MyHandler();

        getApplicationContext().getContentResolver().registerContentObserver(
                Uri.parse("content://downloads/my_downloads"),
                true,
                new DownloadChangeObserver(myHandler, downloadId)
        );

        registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action))
                {
                    long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                    if (downloadId == myDwonloadID)
                    {
                        Intent install = new Intent(Intent.ACTION_VIEW);
                        Uri downloadFileUri = downloadManager.getUriForDownloadedFile(downloadId);
                        install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(install);
                    }
                }
            }
        }, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    private class MyHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                    progressBar2.setMax(msg.arg2/1024/1024);
                    textView4.setText(msg.arg1+"  B");
                    int progress = msg.arg1/1024/1024;
                    progressBar2.setProgress(progress);
                    break;
            }
        }
    }

    /**
     * 更新下载进度
     */
    class DownloadChangeObserver extends ContentObserver
    {
        private Handler handler;
        private long downloadId;

        public DownloadChangeObserver(Handler handler, long downloadId)
        {
            super(handler);
            this.handler = handler;
            this.downloadId = downloadId;
        }

        @Override
        public void onChange(boolean selfChange)
        {
            updateView(handler, downloadId);
        }
    }

    /**
     * 更新ui
     *
     * @param handler
     * @param downloadId
     */
    public void updateView(Handler handler, long downloadId)
    {
        // 获取状态和字节
        int[] bytesAndStatus = getBytesAndStatus(downloadId);
        //
        handler.sendMessage(handler.obtainMessage(0, bytesAndStatus[0],
                bytesAndStatus[1], bytesAndStatus[2]));
    }

    public int[] getBytesAndStatus(long downloadId)
    {
        int[] bytesAndStatus = new int[]{-1, -1, 0};
        DownloadManager.Query query = new DownloadManager.Query()
                .setFilterById(downloadId);
        Cursor c = null;
        try
        {
            c = downloadManager.query(query);
            if (c != null && c.moveToFirst())
            {
                // 当前下载的字节
                bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                // 总字节数
                bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                // 状态
                bytesAndStatus[2] = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally
        {
            if (c != null)
            {
                c.close();
            }
        }
        return bytesAndStatus;
    }

    /**
     * Url转Uri  通过文件路径获取Uri
     *
     * @param context
     * @param imageFile
     * @return
     */
    public static Uri getImageContentUri(Context context, File imageFile)
    {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst())
        {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else
        {
            if (imageFile.exists())
            {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else
            {
                return null;
            }
        }
    }

}
