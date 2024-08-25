package com.cuonglv.learning_spring.utility.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;

import lombok.Data;

@Data
public class TextInfo {

	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String CENTER = "center";

	private String text;
	private float x;
	private float y;
	private float height;
	private float width;
	private int page;

	public TextInfo(String text, float x, float y, float height, float width, int page) {
		super();
		this.text = text;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.page = page;
	}

	public TextInfo() {

	}

	public String getText() {
		return text;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static ArrayList<TextInfo> findThePositionsOfTheCharactersInTheMarkingString(String pathPDF, String text)
			throws IOException {
		ArrayList<TextInfo> infoTextDynamicArr = new ArrayList<>();
		PdfReader reader = null;
		PdfDocument pdfDoc = null;
		try {

			reader = new PdfReader(pathPDF);
			pdfDoc = new PdfDocument(reader);
			for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
				TextMarginFinder listener = new TextMarginFinder();
				new PdfCanvasProcessor(listener).processPageContent(pdfDoc.getPage(i));
				List<TextInfo> position = listener.getPosition();
				infoTextDynamicArr.addAll(findPositionsInPage(position, text, i));
			}
			return infoTextDynamicArr;
		} catch (IOException e) {
			throw e;
		} finally {
			if (pdfDoc != null)
				pdfDoc.close();
			if (reader != null)
				reader.close();
		}

	}

	private static List<TextInfo> findPositionsInPage(List<TextInfo> position, String text, int page) {
		List<TextInfo> positions = new ArrayList<>();
		for (TextInfo pos : position) {
			pos.page = page;
			if (text.contains(pos.getText().trim())) {
				if (pos.getText().trim().length() > 1) {
					String[] stringArrPos = subStringToStringArr(pos.getText().trim());
					for (int j = 0; j < stringArrPos.length; j++) {
						TextInfo temp = new TextInfo(stringArrPos[j], pos.getX() + j, pos.getY(), pos.getHeight(),
								pos.getWidth(), pos.getPage());
						positions.add(temp);
					}
				} else {
					positions.add(pos);
				}
			}
		}
		return positions;
	}

	public static ArrayList<TextInfo> findThePositionsOfTheCharactersInTheMarkingString(InputStream pathPDF,
			String text) throws Exception {
		ArrayList<TextInfo> infoTextDynamicArr = new ArrayList<>();

		try {
			PdfReader reader = new PdfReader(pathPDF);
			PdfDocument pdfDoc = new PdfDocument(reader);
			try {
				for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
					TextMarginFinder listener = new TextMarginFinder();
					new PdfCanvasProcessor(listener).processPageContent(pdfDoc.getPage(i));
					List<TextInfo> position = listener.getPosition();
					for (TextInfo pos : position) {
						pos.page = i;
						if (text.contains(pos.getText().trim())) {
							if (pos.getText().trim().length() > 1) {
								String[] stringArrPos = subStringToStringArr(pos.getText().trim());
								for (int j = 0; j < stringArrPos.length; j++) {
									TextInfo temp = new TextInfo(stringArrPos[j], pos.getX() + j, pos.getY(),
											pos.getHeight(), pos.getWidth(), pos.getPage());
									infoTextDynamicArr.add(temp);

								}
							} else
								infoTextDynamicArr.add(pos);
						}
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (pdfDoc != null)
					pdfDoc.close();
				if (reader != null)
					reader.close();
			}
		} catch (Exception e) {
			throw e;
		}
		return infoTextDynamicArr;
	}

	public static String[] subStringToStringArr(String str) {

		String[] stringArray = new String[str.length()];
		for (int i = 0; i < str.length(); i++) {
			stringArray[i] = str.substring(i, i + 1);
		}
		return stringArray;
	}

	public static boolean isSubstring(String mainString, String subString) {
		if (mainString == null || subString == null) {
			return false;
		}

		return mainString.contains(subString);
	}

	public static TextInfo findTheCenterPositionOfTheMarkedString(List<TextInfo> dynamicTextInfoArr,
			String signCharSet) {

		float totalX = 0;
		float totalY = 0;
		float totalHeight = 0;
		float totalWidth = 0;
		int totalPage = 0;
		if (dynamicTextInfoArr.isEmpty())
			return new TextInfo();
		for (TextInfo ift : dynamicTextInfoArr) {
			totalX += ift.getX();
			totalY += ift.getY();
			totalHeight += ift.getHeight();
			totalWidth += ift.getWidth();
			totalPage += ift.getPage();
		}

		float averagePage = (float) totalPage / dynamicTextInfoArr.size();

		if (((int) averagePage) != averagePage || dynamicTextInfoArr.isEmpty())
			return new TextInfo();
		return new TextInfo(signCharSet, totalX / dynamicTextInfoArr.size(), totalY / dynamicTextInfoArr.size(),
				totalHeight / dynamicTextInfoArr.size(), totalWidth / dynamicTextInfoArr.size(),
				totalPage / dynamicTextInfoArr.size());
	}

	public static TextInfo findTheLeftPositionOfTheMarkedString(List<TextInfo> dynamicTextInfoArr, String signCharSet) {

		if (dynamicTextInfoArr.isEmpty())
			return new TextInfo();

		return new TextInfo(signCharSet, dynamicTextInfoArr.get(0).getX(), dynamicTextInfoArr.get(0).getY(),
				dynamicTextInfoArr.get(0).getHeight(), dynamicTextInfoArr.get(0).getWidth(),
				dynamicTextInfoArr.get(0).getPage());
	}

	public static TextInfo findTheRightPositionOfTheMarkedString(List<TextInfo> dynamicTextInfoArr,
			String signCharSet) {

		int arrSize = dynamicTextInfoArr.size() - 1;

		if (arrSize == -1)
			return new TextInfo();

		return new TextInfo(signCharSet, dynamicTextInfoArr.get(arrSize).getX(), dynamicTextInfoArr.get(arrSize).getY(),
				dynamicTextInfoArr.get(arrSize).getHeight(), dynamicTextInfoArr.get(arrSize).getWidth(),
				dynamicTextInfoArr.get(arrSize).getPage());
	}

	public static List<List<TextInfo>> findThePositionsOfTheMarkingString(ArrayList<TextInfo> arrayList,
			String string) {
		List<List<TextInfo>> result = new ArrayList<>();
		String[] targetTexts = subStringToStringArr(string);

		if (arrayList == null || string == null || targetTexts.length == 0) {
			return result;
		}

		List<Integer> startIndexes = new ArrayList<>();
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getText().equals(targetTexts[0])) {
				startIndexes.add(i);
			}
		}

		for (int startIndex : startIndexes) {
			int aIndex = 0;
			List<TextInfo> subArray = new ArrayList<>();
			for (int i = startIndex; i < arrayList.size(); i++) {
				TextInfo infoText = arrayList.get(i);
				if (infoText.getText().equals(targetTexts[aIndex])) {
					subArray.add(infoText);
					aIndex++;
					if (aIndex == targetTexts.length) {
						result.add(subArray);
						break;
					}
				}
			}
		}

		return result;
	}

	public static double calculateXDifference(TextInfo a, TextInfo b) {
		return Math.abs(a.getX() - b.getX());
	}

	public static float calculateAverageXDifference(List<TextInfo> numbers) {
		if (numbers == null || numbers.isEmpty()) {
			throw new NullPointerException("List X is empty or null");
		}

		float sum = 0;
		int n = numbers.size();
		for (int i = 1; i < n; i++) {
			float beforeAvgXi = numbers.get(i).getX();
			String formattedAvgXi = String.format("%.4f", beforeAvgXi);
			float roundedAvgXi = Float.parseFloat(formattedAvgXi);

			float beforeAvgXi1 = numbers.get(i - 1).getX();
			String formattedAvgXi1 = String.format("%.4f", beforeAvgXi1);
			float roundedAvgXi1 = Float.parseFloat(formattedAvgXi1);

			sum += Math.abs(roundedAvgXi - roundedAvgXi1);
		}
		return sum / (n - 1);
	}

	public static float calculateAverageYDifference(List<TextInfo> numbers) {
		if (numbers == null || numbers.isEmpty()) {
			throw new NullPointerException("List Y is empty or null");
		}

		float sum = 0;
		int n = numbers.size();
		for (int i = 1; i < n; i++) {
			sum += Math.abs(numbers.get(i).getY() - numbers.get(i - 1).getY());
		}

		return sum / (n - 1);
	}

	public static double calculateYDifference(TextInfo a, TextInfo b) {
		return Math.abs(a.getY() - b.getY());
	}

	public static List<TextInfo> findPositionsForTheCharacter(List<List<TextInfo>> subArrays) {
		List<TextInfo> result = new ArrayList<>();
		double minDifference = Double.MAX_VALUE;

		for (List<TextInfo> subArray : subArrays) {
			Collections.sort(subArray, Comparator.comparingDouble(TextInfo::getX));

			double currentDifference = calculateXDifference(subArray.get(0), subArray.get(subArray.size() - 1));
			if (currentDifference < minDifference) {
				minDifference = currentDifference;
				result = subArray;
			}
		}

		return result;
	}

	public static List<TextInfo> findMinXYDifferenceList(List<List<TextInfo>> subArrays) {
		List<TextInfo> result = new ArrayList<>();
		double yMax = subArrays.get(0).get(0).getY();
		if (subArrays.isEmpty()) {
			throw new NullPointerException("List XY is empty or null");
		}
		if (subArrays.size() == 1)
			return subArrays.get(0);
		float yAvg0 = subArrays.get(0).get(0).getY();
		float xAvg0 = subArrays.get(0).get(0).getX();
		double cMin = Math.sqrt(xAvg0 * xAvg0 + yAvg0 * yAvg0);

		String formattedMin = String.format("%.4f", cMin);
		double cMinRounded = Float.parseFloat(formattedMin);

		for (List<TextInfo> subArray : subArrays) {
			Collections.sort(subArray, Comparator.comparingDouble(TextInfo::getX));

			double xMinCurrent = subArray.get(0).getX();

			double yMaxCurrent = subArray.get(0).getY();

			double c = Math.sqrt(xMinCurrent * xMinCurrent + yMaxCurrent * yMaxCurrent);

			String formatted = String.format("%.4f", c);
			double cRounded = Float.parseFloat(formatted);

			if (cMinRounded >= cRounded && yMax <= yMaxCurrent) {
				cMinRounded = cRounded;
				yMax = yMaxCurrent;
				result = subArray;

			}

		}
		return result;
	}

	public static List<List<TextInfo>> findMinPageList(List<List<TextInfo>> subArrays) {
		int minPage = subArrays.get(0).get(0).getPage();

		if (subArrays.isEmpty()) {
			throw new NullPointerException("List is empty or null");
		}
		if (subArrays.size() == 1)
			return subArrays;

		for (List<TextInfo> subArray : subArrays) {

			int currentPage = subArray.get(0).getPage();
			if (currentPage < minPage)
				minPage = currentPage;
		}
		List<List<TextInfo>> result = new ArrayList<>();
		for (List<TextInfo> subArray : subArrays) {

			int currentPage = subArray.get(0).getPage();
			if (currentPage == minPage)
				result.add(subArray);
		}
		return result;
	}

	public static List<List<TextInfo>> findListOfConsecutiveCharacters(List<List<TextInfo>> subArrays) {

		float minConsecutive = calculateAverageXDifference(subArrays.get(0));
		String formattedMin = String.format("%.4f", minConsecutive);

		float minRounded = Float.parseFloat(formattedMin);
		if (subArrays.isEmpty()) {
			throw new NullPointerException("List is empty or null");
		}
		if (subArrays.size() == 1)
			return subArrays;

		for (List<TextInfo> subArray : subArrays) {

			float currentMin = calculateAverageXDifference(subArray);
			String formattedCurrentMin = String.format("%.4f", currentMin);
			float currentMinRounded = Float.parseFloat(formattedCurrentMin);

			float width = subArray.get(0).getWidth();

			if (minRounded + width > currentMinRounded)
				minRounded = currentMinRounded;
		}
		List<List<TextInfo>> result = new ArrayList<>();
		for (List<TextInfo> subArray : subArrays) {

			float currentMin = calculateAverageXDifference(subArray);
			String formattedCurrentMin = String.format("%.4f", currentMin);
			double currentMinRounded = Float.parseFloat(formattedCurrentMin);

			if (minRounded >= currentMinRounded)
				result.add(subArray);
		}
		return result;
	}

	public static List<List<TextInfo>> findCharactersInTheSameRow(List<List<TextInfo>> subArrays) {
		if (subArrays.size() == 1)
			return subArrays;
		List<List<TextInfo>> result = new ArrayList<>();
		for (List<TextInfo> subArray : subArrays) {
			Collections.sort(subArray, Comparator.comparingDouble(TextInfo::getX));

			float currentXAvg = calculateAverageYDifference(subArray);

			if (currentXAvg == 0) {
				result.add(subArray);
			}
		}
		return result;
	}

	public static TextInfo findSignPosition(String pathPDF, String markedString, String align) throws Exception {
		ArrayList<TextInfo> dynamicArr = findThePositionsOfTheCharactersInTheMarkingString(pathPDF, markedString);
		if (dynamicArr.isEmpty()) {
			throw new NullPointerException("No character found");
		}

		List<List<TextInfo>> subArrays = findThePositionsOfTheMarkingString(dynamicArr, markedString);
		if (subArrays.isEmpty()) {
			throw new NullPointerException("No text found");
		}

		List<List<TextInfo>> sameLineChar = findCharactersInTheSameRow(subArrays);
		if (sameLineChar.isEmpty()) {
			throw new NullPointerException("No text in same row found");
		}
		List<List<TextInfo>> consecutiveChar = findListOfConsecutiveCharacters(sameLineChar);

		List<List<TextInfo>> minPage = findMinPageList(consecutiveChar);
		if (minPage == null || minPage.isEmpty()) {

			throw new NullPointerException("No page found");
		}
		List<TextInfo> minXDifferenceList = findMinXYDifferenceList(minPage);
		if (minXDifferenceList == null || minXDifferenceList.isEmpty()) {
			throw new NullPointerException("No text found");
		}

		TextInfo centerPos = findTheCenterPositionOfTheMarkedString(minXDifferenceList, markedString);

		if (LEFT.equalsIgnoreCase(align)) {
			centerPos = findTheLeftPositionOfTheMarkedString(minXDifferenceList, markedString);
		}
		if (RIGHT.equalsIgnoreCase(align)) {
			centerPos = findTheRightPositionOfTheMarkedString(minXDifferenceList, markedString);
		}

		return centerPos;
	}

	public static TextInfo findTextPosition(InputStream bis, String markedString, String align) throws Exception {
		ArrayList<TextInfo> dynamicArr = findThePositionsOfTheCharactersInTheMarkingString(bis, markedString);
		if (dynamicArr.isEmpty())
			return new TextInfo();

		List<List<TextInfo>> subArrays = findThePositionsOfTheMarkingString(dynamicArr, markedString);
		if (subArrays.isEmpty())
			return new TextInfo();

		List<List<TextInfo>> sameLineChar = findCharactersInTheSameRow(subArrays);
		if (sameLineChar.isEmpty())
			return new TextInfo();
		List<List<TextInfo>> consecutiveChar = findListOfConsecutiveCharacters(sameLineChar);

		List<List<TextInfo>> minPage = findMinPageList(consecutiveChar);
		if (minPage == null || minPage.isEmpty())
			return new TextInfo();
		List<TextInfo> minXDifferenceList = findMinXYDifferenceList(minPage);
		if (minXDifferenceList == null || minXDifferenceList.isEmpty())
			return new TextInfo();

		TextInfo centerPos = findTheCenterPositionOfTheMarkedString(minXDifferenceList, markedString);

		if (LEFT.equalsIgnoreCase(align)) {
			centerPos = findTheLeftPositionOfTheMarkedString(minXDifferenceList, markedString);
		}
		if (RIGHT.equalsIgnoreCase(align)) {
			centerPos = findTheRightPositionOfTheMarkedString(minXDifferenceList, markedString);
		}

		return centerPos;
	}

}
