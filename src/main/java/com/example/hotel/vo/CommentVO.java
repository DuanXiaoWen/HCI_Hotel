package com.example.hotel.vo;

/**
 * @author 13524
 */
public class CommentVO {
    private String userName;
    private String comment;
    private Integer commentScore;
    private String checkOutTime;

    @Override
    public String toString() {
        return "CommentVO{" +
                "userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", commentScore=" + commentScore +
                ", checkOutTime='" + checkOutTime + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
