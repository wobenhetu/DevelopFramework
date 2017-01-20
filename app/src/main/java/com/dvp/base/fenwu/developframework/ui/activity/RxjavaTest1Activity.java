package com.dvp.base.fenwu.developframework.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvp.base.fenwu.developframework.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaTest1Activity extends AppCompatActivity
{


    private ImageView imageView;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_test1);

        //init1();
        //init2();
        //init3();
        //init4();
        //init5_displayImage();
        //init6_schedulers();
        //以下测试rxjava的操作符
        //init7_operator_map();
        //init8_operator_flatMap();
        init9();
        //init10();
    }

    private void init1()
    {
        //创建一个观察者
        Observer<String> observer = new Observer<String>()
        {
            @Override
            public void onCompleted()
            {
                Log.i("dfw====", "onCompleted");
            }

            @Override
            public void onError(Throwable e)
            {
                Log.i("dfw====", "onError");
            }

            @Override
            public void onNext(String s)
            {
                Log.i("dfw====", "onNext=" + s);
            }
        };

        //创建一个被观察者
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>()
        {

            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                for (int i = 0; i < 10; i++)
                {
                    subscriber.onNext("发射数字：" + i);
                }
                subscriber.onCompleted();

            }
        });
        //建立绑定关系
        observable1.subscribe(observer);

    }

    /**
     *
     */
    private void init2()
    {

        ///测试just操作符
        //创建一个观察者
        //第一种写法
      /*  Observer<String> observer = new Observer<String>()
        {
            @Override
            public void onCompleted()
            {
                Log.i("dfw====", "onCompleted");
            }

            @Override
            public void onError(Throwable e)
            {
                Log.i("dfw====", "onError");
            }

            @Override
            public void onNext(String s)
            {
                Log.i("dfw====", "onNext=" + s);
            }
        };

        Observable observable2 = Observable.just("hahaha1", "hahaha2", "hahaha3");

        observable2.subscribe(observer);*/


        //第二种写法
        Observable.just("hahaha1", "hahaha2", "hahaha3").subscribe(new Subscriber<String>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(String s)
            {
                Log.i("dfw2016====",s);
            }
        });
    }

    private void init3()
    {
        //创建一个观察者
        Observer<String> observer = new Observer<String>()
        {
            @Override
            public void onCompleted()
            {
                Log.i("dfw====", "onCompleted");
            }

            @Override
            public void onError(Throwable e)
            {
                Log.i("dfw====", "onError");
            }

            @Override
            public void onNext(String s)
            {
                Log.i("dfw====", "onNext=" + s);
            }
        };

        String[] str = {"Hello", "Hi", "Aloha"};

        Observable observable3 = Observable.from(str);

        observable3.subscribe(observer);
    }

    private void init4()
    {
        ///测试Action
//        Action0 是 RxJava 的一个接口，它只有一个方法 call()，这个方法是无参无返回值的；
// 由于 onCompleted() 方法也是无参无返回值的，因此 Action0 可以被当成一个包装对象，
// 将 onCompleted() 的内容打包起来将自己作为一个参数传入 subscribe() 以实现不完整定义的回调。
// 这样其实也可以看做将 onCompleted() 方法作为参数传进了 subscribe()，相当于其他某些语言中的『闭包』。
// Action1 也是一个接口，它同样只有一个方法 call(T param)，这个方法也无返回值，但有一个参数；
// 与 Action0 同理，由于 onNext(T obj) 和 onError(Throwable error) 也是单参数无返回值的，
// 因此 Action1 可以将 onNext(obj) 和 onError(error) 打包起来传入 subscribe() 以实现不完整定义的回调。
// 事实上，虽然 Action0 和 Action1 在 API 中使用最广泛，但 RxJava 是提供了多个 ActionX 形式的接口 (例如 Action2, Action3) 的，
// 它们可以被用以包装不同的无返回值的方法。

        //创建一个观察者
        //from()接收一个集合作为输入，然后每次输出一个元素给subscriber.
        Observable.from(new String[]{"test1", "test2", "test3"}).subscribe(new Action1<String>()
        {
            @Override
            public void call(String s)
            {
                Log.i("dfw====", s);
            }
        });
    }

    private void init5_displayImage()
    {
        imageView = (ImageView) findViewById(R.id.imageView2);
        //来显示一张图片
        Observable.create(new Observable.OnSubscribe<Drawable>()
        {
            @Override
            public void call(Subscriber<? super Drawable> subscriber)
            {
                subscriber.onNext(getResources().getDrawable(R.mipmap.ic_launcher));
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(Drawable drawable)
            {
                imageView.setImageDrawable(drawable);
            }
        });
    }

    static String str = "";

    private void init6_schedulers()
    {
        //Scheduler 线程控制

      /*  在RxJava 中，Scheduler ——调度器，相当于线程控制器，RxJava 通过它来指定每一段代码应该运行在什么样的线程。
      RxJava 已经内置了几个 Scheduler ，它们已经适合大多数的使用场景：

        Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
        Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
        Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
        行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，
        因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
        Schedulers.computation(): 计算所使用的 Scheduler。
        这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。
        这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
        另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
        有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。
        subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。 *
        observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
    */
        tv = (TextView) findViewById(R.id.textView3);
        Observable.just("dddd1 ", "dddd2 ", "dddd3 ", "dddd4 ", "dddd5 ", "dddd6 ")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>()
                {
                    @Override
                    public void call(String s)
                    {
                        Log.i("dfw=====", s);
                        str += s;
                        tv.setText(str);
                    }
                });

    }


    private List<Student> stuList;

    //下面开始操作符的示例   所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。
    //先以操作符map
    private void init7_operator_map()
    {
        stuList = new ArrayList<>();


        //第一个学生的数据
        Student stu = new Student();
        stu.setId("abc0");
        stu.setName("student0");
        List<Courses> mCoursesList0 = new ArrayList<>();
        for (int j = 0; j < 5; j++)
        {
            Courses courses = new Courses();
            courses.setId("stuid(0)kechengid" + j);
            courses.setName("stuid(0)kechengname" + j);
            mCoursesList0.add(courses);
        }
        stu.setmCoursesList(mCoursesList0);
        stuList.add(stu);

        //第二个学生的数据
        Student stu1 = new Student();
        stu1.setId("abc1");
        stu1.setName("student1");
        List<Courses> mCoursesList1 = new ArrayList<>();
        for (int j = 0; j < 5; j++)
        {
            Courses courses = new Courses();
            courses.setId("stuid(1)kechengid" + j);
            courses.setName("stuid(1)kechengname" + j);
            mCoursesList1.add(courses);
        }
        stu1.setmCoursesList(mCoursesList1);
        stuList.add(stu1);

      /*  Subscriber<Student> subscriber = new Subscriber<Student>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(Student student)
            {
                List<Courses> courses = student.getmCoursesList();
                for (int i = 0; i < courses.size(); i++)
                {
                    Courses course = courses.get(i);
                    Log.i("dfw==", course.getName());
                }
            }
        };
        Observable.from(stuList)
                .subscribe(subscriber);*/

        //利用map操作符
        // //使用map进行转换，Func1(参数1，参数2)  参数1：转换前的类型，参数2：转换后的类型
        Observable.from(stuList).map(new Func1<Student, String>() {
            @Override
            public String call(Student student)
            {
                String name = student.getName();
                return name;
            }
        }).subscribe(new Action1<String>()
        {
            @Override
            public void call(String s)
            {
                Log.i("map=====",s);
            }
        });

    }


    private void init8_operator_flatMap()
    {
        //flatMap操作符的使用

        stuList = new ArrayList<>();

        //第一个学生的数据
        Student stu = new Student();
        stu.setId("abc0");
        stu.setName("student0");
        List<Courses> mCoursesList0 = new ArrayList<>();
        for (int j = 0; j < 5; j++)
        {
            Courses courses = new Courses();
            courses.setId("stuid(0)kechengid" + j);
            courses.setName("stuid(0)kechengname" + j);
            mCoursesList0.add(courses);
        }
        stu.setmCoursesList(mCoursesList0);
        stuList.add(stu);

        //第二个学生的数据
        Student stu1 = new Student();
        stu1.setId("abc1");
        stu1.setName("student1");
        List<Courses> mCoursesList1 = new ArrayList<>();
        for (int j = 0; j < 5; j++)
        {
            Courses courses = new Courses();
            courses.setId("stuid(1)kechengid" + j);
            courses.setName("stuid(1)kechengname" + j);
            mCoursesList1.add(courses);
        }
        stu1.setmCoursesList(mCoursesList1);
        stuList.add(stu1);


      /*  Observable.from(stuList)
                .flatMap(new Func1<Student, Observable<Courses>>()
                {
                    @Override
                    public Observable<Courses> call(Student student)
                    {
                        return Observable.from(student.getmCoursesList());
                    }
                }).subscribe(new Subscriber<Courses>()
        {
            @Override
            public void onCompleted()
            {
            }

            @Override
            public void onError(Throwable e)
            {
            }

            @Override
            public void onNext(Courses courses)
            {
                Log.i("dfw===", courses.getName().toString());
            }
        });*/
        Observable.from(stuList).flatMap(new Func1<Student, Observable<Courses>>()
        {
            @Override
            public Observable<Courses> call(Student student)
            {
                return Observable.from(student.getmCoursesList());
            }
        }).subscribe(new Subscriber<Courses>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(Courses courses)
            {
                Log.i("","");
            }
        });

    }

    private void init9()
    {
        //针对事件序列的处理和再发送
        Observable.just("ccabcd","aaabcd","ddabcd").toSortedList()
                .subscribe(new Subscriber<List<String>>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(List<String> strings)
            {
               /* for (int i = 0; i < strings.size(); i++)
                {*/
                    Log.i("init9===",strings.toString());
                //}
            }
        });
    }


    private void init10()
    {

    }
    //=============================================
    public class Student
    {
        private String id;
        private String name;

        public List<Courses> getmCoursesList()
        {
            return mCoursesList;
        }

        public void setmCoursesList(List<Courses> mCoursesList)
        {
            this.mCoursesList = mCoursesList;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        private List<Courses> mCoursesList = new ArrayList<>();

    }

    public class Courses
    {
        private String id;
        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }


    }
}
