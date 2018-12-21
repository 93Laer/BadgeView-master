# BadgeView


**使用说明:**气泡提示消息数量等。

**功能描述:** 可以自定义气泡背景颜色、显示文本（字符串）、边框颜色、边框宽度、在界面中显示的位置

**实例图:**

![](https://upload-images.jianshu.io/upload_images/1744409-39f0e2b0f577c99a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 使用说明
**第一步：布局**用BadgeView将目标view进行包裹，并定义相关属性
```
 <com.chinaway.android.view.BadgeView
                        android:id="@+id/tag_process"
                        android:layout_width="0dp"
                        android:layout_height="match_parent"
                        android:layout_weight="1"
                        android:paddingTop="4dp"
                        app:badgeAnchorPosition="AnchorRightTop"
                        app:badgeBgColor="@color/NC10"
                        app:badgeBorderColor="@color/white"
                        app:badgeBorderWidth="2dp"
                        app:badgeMarginHorizon="16dp"
                        app:badgeTextColor="@color/white"
                        app:badgeTextSize="10sp">

                        <TextView
                            android:id="@+id/tv_processing"
                            android:layout_width="match_parent"
                            android:layout_height="match_parent"
                            android:drawablePadding="10dp"
                            android:drawableTop="@drawable/ic_home_progress"
                            android:gravity="center_horizontal"
                            android:text="@string/label_processing"
                            android:textColor="@color/NC4"
                            android:textSize="@dimen/font_size_12_SP" />
                    </com.chinaway.android.view.BadgeView>
```

**第二步：使用**
```
//找到BadgeView
BadgeView badgeView = (BadgeView) findViewById(R.id.tag_solve);
//设置显示文本
        badgeView.setBadgeText("99+");
```

#### 控件属性说明
```
 <declare-styleable name="BadgeView">
        <!--显示文本-->
        <attr name="badgeText" format="string" />
        <!--文本字体大小-->
        <attr name="badgeTextSize" format="dimension" />
        <!--文本颜色-->
        <attr name="badgeTextColor" format="color" />
        <!--边框宽度-->
        <attr name="badgeBorderWidth" format="dimension" />
        <!--边框颜色-->
        <attr name="badgeBorderColor" format="color" />
        <!--气泡背景色-->
        <attr name="badgeBgColor" format="color" />
        <!--徽章显示的位置-->
        <attr name="badgeAnchorPosition">
            <enum name="AnchorLeftTop" value="0" />
            <enum name="AnchorLeftBottom" value="1" />
            <enum name="AnchorRightTop" value="2" />
            <enum name="AnchorRightBottom" value="3" />
        </attr>
        <!--徽章在水平方向的margin-->
        <attr name="badgeMarginHorizon" format="dimension" />
        <!--徽章在垂直方向的margin-->
        <attr name="badgeMarginVertical" format="dimension" />
        <!--徽章内部水平padding-->
        <attr name="badgePaddingHorizon" format="dimension" />
        <!--徽章内部垂直padding-->
        <attr name="badgePaddingVertical" format="dimension" />
    </declare-styleable>
```
**特别说明：** 当只想显示一个气泡，不显示文本内容的时候，badgeText传入空格就好，传入 `null`则代表不绘制徽章
` app:badgeText="  "`
