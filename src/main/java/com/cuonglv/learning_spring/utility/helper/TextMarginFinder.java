package com.cuonglv.learning_spring.utility.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.itextpdf.kernel.geom.LineSegment;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IEventListener;

public class TextMarginFinder implements IEventListener {
	List<TextInfo> lstP = new ArrayList<TextInfo>();

	private void renderText(TextRenderInfo renderInfo) {
		if (renderInfo.getText().trim().length() == 0)
			return;
		LineSegment ascent = renderInfo.getAscentLine();
		LineSegment descent = renderInfo.getDescentLine();

		float initX = descent.getStartPoint().get(0);
		float initY = descent.getStartPoint().get(1);
		float endX = ascent.getEndPoint().get(0);
		float endY = ascent.getEndPoint().get(1);

		TextInfo p = new TextInfo(renderInfo.getText(), initX, initY, endX - initX, endY - initY, 0);
		lstP.add(p);
	}

	public List<TextInfo> getPosition() {
		return lstP;
	}

	@Override
	public void eventOccurred(IEventData data, EventType type) {
		renderText((TextRenderInfo) data);
	}

	@Override
	public Set<EventType> getSupportedEvents() {
		return Collections.unmodifiableSet(new LinkedHashSet<>(Collections.singletonList(EventType.RENDER_TEXT)));
	}
}
