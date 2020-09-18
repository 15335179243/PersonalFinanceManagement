package com.example.common_base.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SpannableStringAttach {

    private final String TAG = "SpannableStringAttach";
    private CharSequence mSource;
    private final SpannableString mSpannableString;

    public static final int MATCH_MODE_ALL = 0;
    public static final int MATCH_MODE_FIRST = Integer.MIN_VALUE;
    public static final int MATCH_MODE_LAST = Integer.MAX_VALUE;
    private boolean mContainClickSpan;

    private SpannableStringAttach(CharSequence source) {
        this.mSource = resetNull(source);
        mSpannableString = new SpannableString(mSource);
    }

    private CharSequence resetNull(CharSequence source) {
        if (!TextUtils.isEmpty(source)) {
            return source.toString().replace("null", "");
        }
        return "";
    }

    public static SpannableStringAttach create(CharSequence source) {
        return new SpannableStringAttach(source);
    }

    public SpannableStringAttach addSizeSpan(CharSequence spanCharSequence, int dpSize) {
        return addSizeSpan(spanCharSequence, dpSize, MATCH_MODE_ALL);
    }

    public SpannableStringAttach addSizeSpan(CharSequence spanCharSequence, int dpSize, int matchMode) {
        List<Integer[]> spanRanges = getSpanRangeMatchAll(spanCharSequence);
        if (spanRanges.size() ==0) {
            Log.e(TAG, "spanCharSequence unavailable!");
            return this;
        }
        switch (matchMode) {
            case MATCH_MODE_ALL:
                for (Integer[] range : spanRanges) {
                    attachSizeSpan(dpSize, range);
                }
                break;
            case MATCH_MODE_FIRST:
                attachSizeSpan(dpSize, spanRanges.get(0));
                break;
            case MATCH_MODE_LAST:
                attachSizeSpan(dpSize, spanRanges.get(spanRanges.size() - 1));
                break;
            default:
                int size = spanRanges.size();
                if (matchMode >= 0 && matchMode < size) {
                    attachSizeSpan(dpSize, spanRanges.get(matchMode));
                }
                break;
        }
        return this;
    }

    private void attachSizeSpan(int dpSize, Integer[] range) {
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(dpSize, true);
        mSpannableString.setSpan(sizeSpan, range[0], range[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringAttach addForegroundColorSpan(CharSequence spanCharSequence, @ColorInt int color) {
        return addForegroundColorSpan(spanCharSequence, color, MATCH_MODE_ALL);
    }

    public SpannableStringAttach addForegroundColorSpan(CharSequence spanCharSequence, @ColorInt int color, int matchMode) {
        List<Integer[]> spanRanges = getSpanRangeMatchAll(spanCharSequence);
        if (spanRanges.size() ==0) {
            Log.e(TAG, "spanCharSequence unavailable!");
            return this;
        }
        switch (matchMode) {
            case MATCH_MODE_ALL:
                for (Integer[] range : spanRanges) {
                    attachForegroundColorSpan(color, range);
                }
                break;
            case MATCH_MODE_FIRST:
                attachForegroundColorSpan(color, spanRanges.get(0));
                break;
            case MATCH_MODE_LAST:
                attachForegroundColorSpan(color, spanRanges.get(spanRanges.size() - 1));
                break;
            default:
                int size = spanRanges.size();
                if (matchMode >= 0 && matchMode < size) {
                    attachForegroundColorSpan(color, spanRanges.get(matchMode));
                }
                break;
        }
        return this;
    }

    private void attachForegroundColorSpan(@ColorInt int color, Integer[] range) {
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        mSpannableString.setSpan(colorSpan, range[0], range[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    @Deprecated
    public SpannableStringAttach attachUnderlineSpan(CharSequence spanCharSequence, @ColorInt int color) {
        return attachUnderlineSpan(spanCharSequence, color, MATCH_MODE_ALL);
    }

    @Deprecated
    public SpannableStringAttach attachUnderlineSpan(CharSequence spanCharSequence, @ColorInt int color, int matchMode) {
        List<Integer[]> spanRanges = getSpanRangeMatchAll(spanCharSequence);
        if (spanRanges.size() ==0) {
            Log.e(TAG, "spanCharSequence unavailable!");
            return this;
        }
        switch (matchMode) {
            case MATCH_MODE_ALL:
                for (Integer[] range : spanRanges) {
                    attachUnderlineSpan(color, range);
                }
                break;
            case MATCH_MODE_FIRST:
                attachUnderlineSpan(color, spanRanges.get(0));
                break;
            case MATCH_MODE_LAST:
                attachUnderlineSpan(color, spanRanges.get(spanRanges.size() - 1));
                break;
            default:
                int size = spanRanges.size();
                if (matchMode >= 0 && matchMode < size) {
                    attachUnderlineSpan(color, spanRanges.get(matchMode));
                }
                break;
        }
        return this;
    }

    private void attachUnderlineSpan(@ColorInt int color, Integer[] range) {
        mSpannableString.setSpan(new ColoredUnderlineSpan(color), range[0], range[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    static final class ColoredUnderlineSpan extends CharacterStyle
            implements UpdateAppearance {
        private final int mColor;

        public ColoredUnderlineSpan(final int color) {
            mColor = color;
        }

        @Override
        public void updateDrawState(final TextPaint tp) {
            try {
                final Method method = TextPaint.class.getMethod("setUnderlineText",
                        Integer.TYPE,
                        Float.TYPE);
                method.invoke(tp, mColor, 1f);
            } catch (final Exception e) {
                tp.setUnderlineText(true);
            }
        }
    }

    public SpannableStringAttach addBackgroundColorSpan(CharSequence spanCharSequence, @ColorInt int color) {
        return addBackgroundColorSpan(spanCharSequence, color, MATCH_MODE_ALL);
    }

    public SpannableStringAttach addBackgroundColorSpan(CharSequence spanCharSequence, @ColorInt int color, int matchMode) {
        List<Integer[]> spanRanges = getSpanRangeMatchAll(spanCharSequence);
        if (spanRanges.size() ==0) {
            Log.e(TAG, "spanCharSequence unavailable!");
            return this;
        }
        switch (matchMode) {
            case MATCH_MODE_ALL:
                for (Integer[] range : spanRanges) {
                    attachBackgroundColorSpan(color, range);
                }
                break;
            case MATCH_MODE_FIRST:
                attachBackgroundColorSpan(color, spanRanges.get(0));
                break;
            case MATCH_MODE_LAST:
                attachBackgroundColorSpan(color, spanRanges.get(spanRanges.size() - 1));
                break;
            default:
                int size = spanRanges.size();
                if (matchMode >= 0 && matchMode < size) {
                    attachBackgroundColorSpan(color, spanRanges.get(matchMode));
                }
                break;
        }
        return this;
    }

    private void attachBackgroundColorSpan(@ColorInt int color, Integer[] range) {
        BackgroundColorSpan colorSpan = new BackgroundColorSpan(color);
        mSpannableString.setSpan(colorSpan, range[0], range[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringAttach addClickableSpan(CharSequence spanCharSequence, View.OnClickListener onClickListener) {
        return addClickableSpan(spanCharSequence, false, onClickListener);
    }

    public SpannableStringAttach addClickableSpan(CharSequence spanCharSequence, @ColorInt int linkColor, View.OnClickListener onClickListener) {
        return addClickableSpan(spanCharSequence, false, MATCH_MODE_ALL, linkColor, onClickListener);
    }

    public SpannableStringAttach addClickableSpan(CharSequence spanCharSequence, boolean underlineText, View.OnClickListener onClickListener) {
        return addClickableSpan(spanCharSequence, underlineText, MATCH_MODE_ALL, Integer.MIN_VALUE, onClickListener);
    }

    /**
     * 当添加ClickableSpan后，会自动设置 LinkMovementMethod {@link SpannableStringAttach#attach(View)}
     * <p>
     * 注意：LinkMovementMethod 会覆盖控件的 OnClickListener
     * 如不想自动设置，可使用方法禁止 {@link SpannableStringAttach#attach(View, boolean)}
     * 或者指定自定义的MovementMethod {@link SpannableStringAttach#attach(View, MovementMethod)}
     *
     * @param spanCharSequence
     * @param UnderlineText
     * @param matchMode
     * @param linkColor
     * @param onClickListener
     * @return
     */
    public SpannableStringAttach addClickableSpan(CharSequence spanCharSequence, boolean UnderlineText, int matchMode, @ColorInt int linkColor, View.OnClickListener onClickListener) {
        List<Integer[]> spanRanges = getSpanRangeMatchAll(spanCharSequence);
        if (spanRanges.size() ==0) {
            Log.e(TAG, "spanCharSequence unavailable!");
            return this;
        }
        switch (matchMode) {
            case MATCH_MODE_ALL:
                for (Integer[] range : spanRanges) {
                    attachClickSpan(UnderlineText, linkColor, onClickListener, range);
                }
                break;
            case MATCH_MODE_FIRST:
                attachClickSpan(UnderlineText, linkColor, onClickListener, spanRanges.get(0));
                break;
            case MATCH_MODE_LAST:
                attachClickSpan(UnderlineText, linkColor, onClickListener, spanRanges.get(spanRanges.size() - 1));
                break;
            default:
                int size = spanRanges.size();
                if (matchMode >= 0 && matchMode < size) {
                    attachClickSpan(UnderlineText, linkColor, onClickListener, spanRanges.get(matchMode));
                }
                break;
        }
        return this;
    }

    private void attachClickSpan(boolean UnderlineText, @ColorInt int linkColor, View.OnClickListener onClickListener, Integer[] spanRange) {
        ClickableSpan colorSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (onClickListener != null)
                    onClickListener.onClick(widget);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(linkColor != Integer.MIN_VALUE ? linkColor : ds.linkColor);
                ds.setUnderlineText(UnderlineText);
            }
        };
        mSpannableString.setSpan(colorSpan, spanRange[0], spanRange[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mContainClickSpan = true;
    }

    private List<Integer[]> getSpanRangeMatchAll(CharSequence spanCharSequence) {
        String spanText = spanCharSequence != null ? spanCharSequence.toString() : "";
        String sourceText = mSource != null ? mSource.toString() : "";
        List<Integer[]> result = new ArrayList<>();
        if (!TextUtils.isEmpty(sourceText)
                && !TextUtils.isEmpty(spanText)
                && sourceText.contains(spanText)) {
            int indexOf = sourceText.indexOf(spanText);
            while (indexOf != -1) {
                result.add(new Integer[]{indexOf, indexOf + spanText.length()});
                indexOf = sourceText.indexOf(spanText, indexOf + spanText.length());
            }
        }
        return result;
    }

    public SpannableString buildSpannableString() {
        return mSpannableString;
    }

    public void attach(View view) {
        //包含ClickableSpan时，会自动设置LinkMovementMethod
        attach(view, mContainClickSpan);
    }

    public void attach(View view, boolean useLinkMovementMethod) {
        attach(view, useLinkMovementMethod ? LinkMovementMethod.getInstance() : null);
    }

    public void attach(View view, MovementMethod movementMethod) {
        if (view instanceof TextView) {
            if (movementMethod != null) {
                ((TextView) view).setMovementMethod(movementMethod);
            }
            if (!TextUtils.isEmpty(mSpannableString)) {
                ((TextView) view).setText(mSpannableString);
            }
        }
    }

}