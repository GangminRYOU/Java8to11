package com.example.java8to11.optional;

import java.util.Optional;

import javax.swing.text.html.Option;

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        //그냥 Optinoal.of()를 사용하면 NPE가 발생한다.
        return Optional.ofNullable(progress);
    }

    public void setProgress(Optional<Progress> progress) {
        /**
         * 이것도 위험하다, 밖에서 NULL을 그냥 넣을 수도 있다.
         * 그러면, progress자체가 null이고, null에 대고 무언가를 하고 있다. -> NPE
         */
        progress.ifPresent((p) -> {
            this.progress = p;
        });
        //이것도 NPE
        progress.isPresent();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
