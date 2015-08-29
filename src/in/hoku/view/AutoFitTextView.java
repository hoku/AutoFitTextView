/**
 * AutoFitTextView v1.0.0
 * http://hoku.in/
 *
 * Copyright 2015 hoku.
 * Released under the MIT License.
 */

package in.hoku.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This class adjust size of the text automatically, if the text is long.
 *
 * @author hoku
 */
public class AutoFitTextView extends TextView {

	/** Minimum limit size. */
	private static final float MIN_TEXT_SIZE = 2f;
	/** Objects for determining the size. */
	private Paint paint = new Paint();

	/** Constructor. */
	public AutoFitTextView(Context c) {
		super(c);
	}

	/** Constructor. */
	public AutoFitTextView(Context c, AttributeSet attrs) {
		super(c, attrs);
	}

	/** onMeasure. */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		resize(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * Resize!!
	 */
	private void resize(int widthMeasureSpec, int heightMeasureSpec) {
		// Get the raw font height.
		paint.setTextSize(getTextSize());
		FontMetrics rawFm = paint.getFontMetrics();
		float rawFontHaigh = rawFm.bottom - rawFm.top + getPaddingTop() + getPaddingBottom();

		// Get the now font size.
		float fontSize = getTextSize();

		// Get the view width.
		float contentWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

		// Do loop if text width bigger than view width.
		while (contentWidth < getTextWidth(fontSize)) {
			// Gradually reduce the font size.
			fontSize--;

			// End the loop if font size smaller than view width.
			if (fontSize <= MIN_TEXT_SIZE) {
				fontSize = MIN_TEXT_SIZE;
				break;
			}
		}

		// Setting resized font size.
		setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);

		// Setting view size.
		if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
			setMeasuredDimension(widthMeasureSpec, (int) rawFontHaigh);
		} else {
			setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		}
	}

	/** onDraw. */
	@Override
	protected void onDraw(Canvas canvas) {
		// Get the font height.
		paint.setTextSize(getTextSize());
		FontMetrics fm = paint.getFontMetrics();
		float fontHaight = fm.bottom - fm.top;

		// Text of the draw target.
		String text = getText().toString();

		// Draw text to center of horizontal if specified CENTER_HORIZONTAL.
		if ((getGravity() & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == Gravity.CENTER_HORIZONTAL) {
			float textSize = getTextWidth(getTextSize());
			canvas.drawText(text, (getWidth() - textSize) / 2, -fm.top + ((getHeight() - fontHaight) / 2), paint);
		}
		// Draw text to position of left.
		else {
			canvas.drawText(text, getPaddingLeft(), -fm.top + ((getHeight() - fontHaight) / 2), paint);
		}
	}

	/**
	 * Get the text width from font size and text.
	 *
	 * @param fontSize Font size of the check target.
	 * @return Text width
	 */
	float getTextWidth(float textSize) {
		paint.setTextSize(textSize);
		return paint.measureText(getText().toString());
	}
}
