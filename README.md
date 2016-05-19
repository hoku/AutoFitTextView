# AutoFitTextView
Custom TextView.
This class adjust size of the text automatically, if the text is long.

文字が長すぎるときに、自動的に文字サイズを小さく縮小して１行に収まるようにしてくれるTextViewです。
文字が小さくなっても、TextViewの高さは元のサイズを維持します。

全体的に英語が怪しすぎますが、まあその、気にしないでください。

<img alt="image" src="https://raw.githubusercontent.com/hoku/GithubImages/master/AutoFitTextView/scrn.png" width="300px" />

# How to

    <in.hoku.view.AutoFitTextView
        android:id="@+id/textView3"
        android:layout_width="180dp"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="#8f8"
        android:padding="5dp"
        android:text="blah blah blah blah blah blah blah"
        android:textSize="22sp" />

# License
MIT License.
