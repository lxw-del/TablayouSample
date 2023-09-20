# TablayouSample
用于练习Canvas绘制的项目，其中使用到了Tablayout和Viewpager2与Fragment的结合，形成一个多页的简单框架。

\##项目目的

1、练习熟悉Canvas的用法，例如drawpath等

2、实现一个通用的多窗口活动，用于以后的方便使用



##模块简介

1、TabLayout和ViewPager2在布局中的使用

```javascript
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#f8f8f8"
    tools:context=".MainActivity">

    <com.google.android.material.tabs.TabLayout
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:id="@+id/tab_layout"
        app:tabTextAppearance="@android:style/TextAppearance.Widget.TabWidget"
        app:tabMode="scrollable"
        />

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</LinearLayout>
```

通过上方是tablayout 下方的内容显示用ViewPager2与Fragment结合使用.

###ViewPager2

ViewPager2是Android Jetpack库中的一个组件，是用于在应用程序中实现页面切换和滑动效果的容器。

需要添加如下依赖

```gradle
implementation("androidx.viewpager2:viewpager2:1.0.0")
```

想了解更多内容可以 参考这篇文章[ViewPager2详解](https://blog.csdn.net/xiaokangss/article/details/131288639?ops_request_misc=\&request_id=\&biz_id=102\&utm_term=ViewPager2\&utm_medium=distribute.pc_search_result.none-task-blog-2\~all\~sobaiduweb\~default-0-131288639.142^v94^insert_down1\&spm=1018.2226.3001.4187)

###Fragment布局中使用的ViewStub

ViewStub是一个轻量级的视图控件，而实际开发中在合适的场景中使用，可以提高渲染速度，占用的内存更少，从而提高App的UI性能。

理解ViewStub这个控件，首先要清楚它的几条属性和应用条件：

1、ViewStub本身在布局资源中是不可见的，要完成在视图中“占位”的任务，宽高都是0，要通过inflate方法或者setVisible方法来显示出来，即在xml布局加载过程中是不占用资源的，它是动态布局一种策略；

2、ViewStub通过inflate实例化视图，只能inflate一次；

3、ViewStub针对的事layout布局，不是某一个具体的view；

viewstub就是**==动态加载试图==**；也就是在我们的app启动绘制页面的时候，他不会绘制到view树中；==当在代码中执行inflate操作后，她才会被添加到试图中==。其实ViewStub就是一个宽高都为0的一个View，它**默认是不可见的**，只有通过调用setVisibility函数或者Inflate函数才 会将其要装载的目标布局给加载出来，从而达到**延迟加载**的效果，这个要被加载的布局通过android\:layout属性来设置。最终目的是把app加载页面的速度提高了，使用户体验更好。

```xml
  <ViewStub
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/sampleStub"
        android:inflatedId="@+id/model"
        />
```

其中android\:id是该view的id，且ViewStub只能用一次inflate，当inflate之后需要使用inflateId来设置他的可见属性。

以下是在Fragement中onCreateView，初始化布局时ViewStub的用法。

```java
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,container,false);

        //ViewStub 的布局加载示例
        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(sampleLayoutSrc);
        sampleStub.inflate();

        ViewStub practiceStub = (ViewStub) view.findViewById(R.id.practiceStub);
        practiceStub.setLayoutResource(practiceLayoutSrc);
        practiceStub.inflate();
        return view;
    }
```



###ViewPager2在MainActivity中的使用

ViewPager2需要适配器，与Fragment使用的时候，直接使用FragmentStateAdapter，其他的都已经弃用了

```java
viewPager = (ViewPager2) findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.sampleLayoutSrc,pageModel.practiceLayoutSrc);
            }

            @Override
            public int getItemCount() {
                return pageModels.size();
            }

        });
```

\###TabLayout与ViewPager2的结合使用

使用`TabLayoutMediator`将`TabLayout`与`ViewPager2`进行关联。`TabLayoutMediator`允许你在`TabLayout`中显示与`ViewPager2`中的页面相对应的标签。

在`TabLayoutMediator`的构造函数中，我们传递了`TabLayout`、`ViewPager2`以及一个`TabConfigurationStrategy`对象。`TabConfigurationStrategy`对象用于配置每个标签的标题。在上面的示例中，我们使用了一个简单的Lambda表达式来设置每个标签的标题为"Tab X"，其中X表示页面的位置。

```java
tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> {
            PageModel pageModel = pageModels.get(position);
            tab.setText(getString(pageModel.titleSrc));
        }).attach();
```

