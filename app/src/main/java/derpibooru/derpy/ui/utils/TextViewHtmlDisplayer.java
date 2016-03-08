package derpibooru.derpy.ui.utils;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Presents HTML layout (links) in TextView.
 */
public abstract class TextViewHtmlDisplayer {
    /**
     * Called when a URL link is selected.
     */
    protected abstract void onLinkClick(String url);

    public void textFromHtml(TextView target, String html) {
        target.setText(getSpannableStringBuilderFromHtml(html));
        /* http://stackoverflow.com/a/2746708/1726690 */
        target.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private SpannableStringBuilder getSpannableStringBuilderFromHtml(String html) {
        /* http://stackoverflow.com/a/19989677/1726690 */
        CharSequence sequence = Html.fromHtml(html);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        return strBuilder;
    }

    private void makeLinkClickable(SpannableStringBuilder strBuilder, URLSpan span) {
        final String url = span.getURL();
        /* http://stackoverflow.com/a/19989677/1726690 */
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                onLinkClick(url);
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }
}