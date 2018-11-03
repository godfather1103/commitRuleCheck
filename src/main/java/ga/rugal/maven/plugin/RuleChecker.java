package ga.rugal.maven.plugin;

import ga.rugal.maven.plugin.rule.CaseRule;
import ga.rugal.maven.plugin.rule.LengthRule;
import com.godfather1103.error.FailureException;
import lombok.AllArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * To check rules against a matched string.
 *
 * @author Rugal Bernstein
 */
@AllArgsConstructor
public class RuleChecker {

    /**
     * 长短大小写等规则
     */
    private CaptureGroup[] captureGroups;
    /**
     * 正确格式的正则表达式
     */
    private String matchPattern;
    /**
     * 检测报错时的提示信息
     */
    private String showMsg;

    private final String pattern = "[\\w\\d\\s-_]+";


    /**
     * Check a matched string with given rules.
     *
     * @param match The input string
     * @return How many failed checks in this string
     */
    public void check(String match) throws FailureException {
        Pattern pattern = Pattern.compile(this.matchPattern);
        Matcher matcher = pattern.matcher(match);
        if (!matcher.find()) {
            throw new FailureException(showMsg);
        }
        if (captureGroups != null) {
            for (int i = 1; i <= Math.min(captureGroups.length, matcher.groupCount()); i++) {
                String extractedContent = this.extractContent(matcher.group(i));
                checkCaptureGroup(extractedContent, this.captureGroups[i - 1]);
            }
        }
    }

    public void checkCaptureGroup(String match, CaptureGroup captureGroup) throws FailureException {
        if (!CaseRule.validate(match, captureGroup.getCaseFormat())) {
            throw new FailureException(String.format("Case format should be %s", captureGroup.getCaseFormat().name()));
        }
        if (!LengthRule.fitMax(match, captureGroup.getMax())) {
            throw new FailureException(String.format("Length should be no more than %d", captureGroup.getMax()));
        }
        if (!LengthRule.fitMin(match, captureGroup.getMin())) {
            throw new FailureException(String.format("Length should be no less than %d", captureGroup.getMin()));
        }
    }

    private String extractContent(String capturedString) throws FailureException {
        if (capturedString==null){
            return "";
        }
        Pattern pattern = Pattern.compile(this.pattern);
        Matcher matcher = pattern.matcher(capturedString);

        if (!matcher.find()) {
            throw new FailureException(String.format("No content found [%s]", capturedString));
        }
        return matcher.group().trim();
    }
}
