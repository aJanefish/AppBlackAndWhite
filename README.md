# AppBlackAndWhite
应用黑白化研究

[CSDN地址](https://blog.csdn.net/yuzhangzhen/article/details/106537582)
@[TOC](应用黑白显示)
督促自己学习总结，特用文章的形式记录下来，共同进步

特殊日期应用页面需要黑白显示，是很多应用必须满足工信部的要求，也成为一般新闻类，娱乐类app的基本功能

# 范例
先看两张图
><div align=center><img width = '300' height ='600' src ="https://img-blog.csdnimg.cn/20200604080629209.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3l1emhhbmd6aGVu,size_8,color_FFFFFF,t_70"/></div>
>正常效果

><div align=center><img width = '300' height ='600' src ="https://img-blog.csdnimg.cn/20200604080629659.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3l1emhhbmd6aGVu,size_8,color_FFFFFF,t_70"/></div>
>置灰后的效果

# 代码
在activity中拿到Window中的DecorVIew
```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getSP().getBoolean(KEY_TYPE, false)) {
            val mPaint = Paint()
            val cm = ColorMatrix()
            cm.setSaturation(0F)
            mPaint.colorFilter = ColorMatrixColorFilter(cm)
            window.decorView.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
        }
    }
```
在实际应用中KEY_TYPE的取值可以通过拉取服务器配置或者根据日期判断，达到项目要求
# 原理
android提供了颜色转化类ColorMatrix

```php
 * 4x5 matrix for transforming the color and alpha components of a Bitmap.
 * The matrix can be passed as single array, and is treated as follows:
 *
 * <pre>
 *  [ a, b, c, d, e,
 *    f, g, h, i, j,
 *    k, l, m, n, o,
 *    p, q, r, s, t ]</pre>
提供一个4x5的举矩阵，就是一个长度为20的数组
```

```php
 * <p>
 * When applied to a color <code>[R, G, B, A]</code>, the resulting color
 * is computed as:
 * </p>
 *
 * <pre>
 *   R&rsquo; = a*R + b*G + c*B + d*A + e;
 *   G&rsquo; = f*R + g*G + h*B + i*A + j;
 *   B&rsquo; = k*R + l*G + m*B + n*A + o;
 *   A&rsquo; = p*R + q*G + r*B + s*A + t;</pre>
 *
 * <p>
 * That resulting color <code>[R&rsquo;, G&rsquo;, B&rsquo;, A&rsquo;]</code>
 * then has each channel clamped to the <code>0</code> to <code>255</code>
 * range.
 * </p>
一个颜色值ARGB通过数组计算得到转化后的ARGB
```
## ColorMatrix转换原理
$$
array=\left[
 \begin{matrix}
    a, b, c, d, e\\
    f, g, h, i, j\\
     k, l, m, n, o\\
     p, q, r, s, t\\
  \end{matrix} 
\right] C=\left[
 \begin{matrix}
    R\\G\\B\\A\\0\\
  \end{matrix} 
\right]
$$
转换后的颜色为：
$$
array*C=
\left[
 \begin{matrix}
    a, b, c, d, e\\
    f, g, h, i, j\\
     k, l, m, n, o\\
     p, q, r, s, t\\
  \end{matrix} 
\right] 
\left[
 \begin{matrix}
    R\\G\\B\\A\\1\\
  \end{matrix} 
\right]
=\left[
 \begin{matrix}
    a*R, b*G, c*B, d*A, e\\
    f*R, g*G, h*B, i*A, j\\
     k*R ,l*G, m*B, n*A, o\\
     p*R, q*G, r*B, s*A, t\\
  \end{matrix} 
\right]
$$
计算结果：
```php
nR = (a*R + b*G + c*B + d*A + e)&0xff
nG = (f*R + g*G + h*B + i*A + j)&0xff
nB = (k*R + l*G + m*B + n*A + o)&0xff
nA = (p*R + q*G + r*B + s*A + t)&0xff
```

## 测试setSaturation(0)，效果是黑白化
$$
\left[
 \begin{matrix}
0.213 & 0.715 & 0.072 & 0.0 & 0.0 \\
0.213 & 0.715 & 0.072 & 0.0 & 0.0 \\ 
0.213 & 0.715 & 0.072 & 0.0 & 0.0 \\ 
0.0 & 0.0 & 0.0 & 1.0 & 0.0 \\
  \end{matrix} 
\right]
$$
```php
测试 setSaturation(0) [0.213, 0.715, 0.072, 0.0, 0.0, 0.213, 0.715, 0.072, 0.0, 0.0, 0.213, 0.715, 0.072, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0] --- start
BLACK 转换前 [a=255,r=0,g=0,b=0]  转换后 [a=255,r=0,g=0,b=0]
BLUE 转换前 [a=255,r=0,g=0,b=255]  转换后 [a=255,r=18,g=18,b=18]
RED 转换前 [a=255,r=255,g=0,b=0]  转换后 [a=255,r=54,g=54,b=54]
GREEN 转换前 [a=255,r=0,g=255,b=0]  转换后 [a=255,r=182,g=182,b=182]
YELLOW 转换前 [a=255,r=255,g=255,b=0]  转换后 [a=255,r=236,g=236,b=236]
MAGENTA 转换前 [a=255,r=255,g=0,b=255]  转换后 [a=255,r=72,g=72,b=72]
ORANGE 转换前 [a=255,r=255,g=200,b=0]  转换后 [a=255,r=197,g=197,b=197]
PINK 转换前 [a=255,r=255,g=175,b=175]  转换后 [a=255,r=192,g=192,b=192]
```
展示效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612073919226.png)
## 测试 setYUV2RGB   YUV to RGB
$$
\left[
 \begin{matrix}
1.0 & 0.0 & 1.402 & 0.0 & 0.0 \\ 
1.0 & -0.34414 & -0.71414 & 0.0 & 0.0 \\ 
1.0 & 1.772 & 0.0 & 0.0 & 0.0 \\ 
0.0 & 0.0 & 0.0 & 1.0 & 0.0 \\ 
  \end{matrix} 
\right]
$$
```php
测试 setYUV2RGB [1.0, 0.0, 1.402, 0.0, 0.0, 1.0, -0.34414, -0.71414, 0.0, 0.0, 1.0, 1.772, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0] --- start
BLACK 转换前 [a=255,r=0,g=0,b=0]  转换后 [a=255,r=0,g=0,b=0]
BLUE 转换前 [a=255,r=0,g=0,b=255]  转换后 [a=255,r=101,g=74,b=0]
RED 转换前 [a=255,r=255,g=0,b=0]  转换后 [a=255,r=255,g=255,b=255]
GREEN 转换前 [a=255,r=0,g=255,b=0]  转换后 [a=255,r=0,g=169,b=195]
YELLOW 转换前 [a=255,r=255,g=255,b=0]  转换后 [a=255,r=255,g=167,b=194]
MAGENTA 转换前 [a=255,r=255,g=0,b=255]  转换后 [a=255,r=100,g=72,b=255]
ORANGE 转换前 [a=255,r=255,g=200,b=0]  转换后 [a=255,r=255,g=186,b=97]
PINK 转换前 [a=255,r=255,g=175,b=175]  转换后 [a=255,r=244,g=69,b=53]
```
展示效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612073942785.png)
## 反转
$$
\left[
 \begin{matrix}
-1.0 & 0.0 & 0.0 & 1.0 & 1.0 \\ 
0.0 & -1.0 & 0.0 & 1.0 & 1.0 \\ 
0.0 & 0.0 & -1.0 & 1.0 & 1.0 \\ 
0.0 & 0.0 & 0.0 & 1.0 & 0.0 \\ 
  \end{matrix} 
\right]
$$

```php
BLACK 转换前 [a=255,r=0,g=0,b=0]  转换后 [a=255,r=0,g=0,b=0]
BLUE 转换前 [a=255,r=0,g=0,b=255]  转换后 [a=255,r=0,g=0,b=1]
RED 转换前 [a=255,r=255,g=0,b=0]  转换后 [a=255,r=1,g=0,b=0]
GREEN 转换前 [a=255,r=0,g=255,b=0]  转换后 [a=255,r=0,g=1,b=0]
YELLOW 转换前 [a=255,r=255,g=255,b=0]  转换后 [a=255,r=1,g=1,b=0]
MAGENTA 转换前 [a=255,r=255,g=0,b=255]  转换后 [a=255,r=1,g=0,b=1]
ORANGE 转换前 [a=255,r=255,g=200,b=0]  转换后 [a=255,r=1,g=56,b=0]
PINK 转换前 [a=255,r=255,g=175,b=175]  转换后 [a=255,r=1,g=81,b=81]
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612080137295.png)
## 去色效果
$$
\left[
 \begin{matrix}
1.5 & 1.5 & 1.5 & 0.0 & -1.0 \\ 
1.5 & 1.5 & 1.5 & 0.0 & -1.0 \\ 
1.5 & 1.5 & 1.5 & 0.0 & -1.0 \\ 
0.0 & 0.0 & 0.0 & 1.0 & 0.0 \\ 
  \end{matrix} 
\right]
$$

```php
BLACK 转换前 [a=255,r=0,g=0,b=0]  转换后 [a=1,r=255,g=255,b=255]
BLUE 转换前 [a=255,r=0,g=0,b=255]  转换后 [a=1,r=125,g=125,b=125]
RED 转换前 [a=255,r=255,g=0,b=0]  转换后 [a=1,r=125,g=125,b=125]
GREEN 转换前 [a=255,r=0,g=255,b=0]  转换后 [a=1,r=125,g=125,b=125]
YELLOW 转换前 [a=255,r=255,g=255,b=0]  转换后 [a=1,r=252,g=252,b=252]
MAGENTA 转换前 [a=255,r=255,g=0,b=255]  转换后 [a=1,r=252,g=252,b=252]
ORANGE 转换前 [a=255,r=255,g=200,b=0]  转换后 [a=1,r=169,g=169,b=169]
PINK 转换前 [a=255,r=255,g=175,b=175]  转换后 [a=1,r=138,g=138,b=138]
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612080643267.png)
还有很多颜色转换的功能，这里就不一一列举了，实际根据工作中的需要选择合适转换
# 代码
[GitHub地址](https://github.com/aJanefish/AppBlackAndWhite)
