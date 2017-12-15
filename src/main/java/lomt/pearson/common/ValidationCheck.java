package lomt.pearson.common;

import org.testng.Assert;

import lomt.pearson.constant.HEConstant;
import lomt.pearson.page_object.HEPom;

public class ValidationCheck {
	
	//applying multiple if and else because Error's order is not maintained on the UI
	public boolean heValidationErrorMessageAssertions(HEPom hePom) {		
		boolean flag;
		try {
			//Language column assertion check 
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.LANGUAGE_BLANK);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.LANGUAGE_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.LANGUAGE_BLANK);
			}
			
			//Question column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.QUESTION_BLANK);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.QUESTION_BLANK)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.QUESTION_BLANK);
			}
			
			//ASSERTION8 column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.ASSERTION8_VAL);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.ASSERTION8_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.ASSERTION8_VAL);
			}
					
			//ASSERTION1 column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.ASSERTION1_VAL);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.ASSERTION1_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.ASSERTION1_VAL);
			}
			
			//ASSERTION2 column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.ASSERTION2_VAL);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.ASSERTION2_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.ASSERTION2_VAL);
			}
			
			//DATE format column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.DATE_FORMAT);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.DATE_FORMAT)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.DATE_FORMAT);
			}
			
			//TOC_NUM_HEADER column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.TOC_NUM_HEADER);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.TOC_NUM_HEADER)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.TOC_NUM_HEADER);
			}
			
			//NEW_EO_AND_NEW_LO_SAME_ROW column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.NEW_EO_AND_NEW_LO_SAME_ROW);
			}
			
			//PROFICIENCY_COL_VAL column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.PROFICIENCY_COL_VAL);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.PROFICIENCY_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.PROFICIENCY_COL_VAL);
			}
			
			//DOMAIN_COL_VAL column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.DOMAIN_COL_VAL);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.DOMAIN_COL_VAL)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.DOMAIN_COL_VAL);
			}
			
			//BLOOMS_CONG_P_DIMENSIONS_TEXT column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.BLOOMS_CONG_P_DIMENSIONS_TEXT);
			}
			
			//BLOOMS_KNOWD_DIMENSIONS_TEXT column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.BLOOMS_KNOWD_DIMENSIONS_TEXT);
			}
			
			//CONCEPT_SUBJECT_MISCONCEPTION_Y column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.CONCEPT_SUBJECT_MISCONCEPTION_Y);
			}
			
			//CONCEPT_SUB_MISCONCEPTION_N column assertion check
			if (hePom.getErrorMessage1().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage1().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage2().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage2().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage3().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage3().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage4().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage4().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage5().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage5().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage6().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage6().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage7().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage7().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage8().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage8().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage9().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage9().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage10().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage10().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage11().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage11().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage12().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage12().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage13().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage13().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			} else if (hePom.getErrorMessage14().getText().equalsIgnoreCase(HEConstant.CONCEPT_SUB_MISCONCEPTION_N)) {
				Assert.assertEquals(hePom.getErrorMessage14().getText(), HEConstant.CONCEPT_SUB_MISCONCEPTION_N);
			}
		} catch (Exception e) {
			flag = false;
			return flag;
		}
		
		return true;
	}

}
