package com.zcx.exam.entity;

/**
 * 考试实体类，用于表示单个考试题目及其相关信息。
 */
public class Exam {
    /**
     * 考试题目的唯一标识符。
     */
    private Long id;

    /**
     * 考试题目的内容描述。
     */
    private String content;

    /**
     * 考试题目的正确答案。
     */
    private String answer;

    /**
     * 考试题目的详细解释或解析。
     */
    private String explanation;

    /**
     * 获取考试题目的唯一标识符。
     *
     * @return 考试题目的唯一标识符。
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置考试题目的唯一标识符。
     *
     * @param id 考试题目的唯一标识符。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取考试题目的内容描述。
     *
     * @return 考试题目的内容描述。
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置考试题目的内容描述。
     *
     * @param content 考试题目的内容描述。
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取考试题目的正确答案。
     *
     * @return 考试题目的正确答案。
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置考试题目的正确答案。
     *
     * @param answer 考试题目的正确答案。
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取考试题目的详细解释或解析。
     *
     * @return 考试题目的详细解释或解析。
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * 设置考试题目的详细解释或解析。
     *
     * @param explanation 考试题目的详细解释或解析。
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
