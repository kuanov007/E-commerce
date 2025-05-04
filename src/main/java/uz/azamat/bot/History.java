package uz.azamat.bot;

import java.util.Objects;

public class History {
    private Long userChatId;
    private Integer messageId;
    private int page;

    public History() {
    }

    public History(Long userChatId, Integer messageId, int page) {
        this.userChatId = userChatId;
        this.messageId = messageId;
        this.page = page;
    }

    public Long getUserChatId() {
        return userChatId;
    }

    public void setUserChatId(Long userChatId) {
        this.userChatId = userChatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History history)) return false;
        return getPage() == history.getPage() && Objects.equals(getUserChatId(), history.getUserChatId()) && Objects.equals(getMessageId(), history.getMessageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserChatId(), getMessageId(), getPage());
    }

    @Override
    public String toString() {
        return "History{" +
               "userChatId=" + userChatId +
               ", messageId=" + messageId +
               ", page=" + page +
               '}';
    }
}
