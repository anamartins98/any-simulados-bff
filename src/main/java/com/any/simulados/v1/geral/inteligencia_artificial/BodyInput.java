package com.any.simulados.v1.geral.inteligencia_artificial;

import java.util.List;

public class BodyInput {
    private List<Content> contents;

    public BodyInput(String prompt) {
        this.contents = List.of(new Content(prompt));
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public static class Content {
        private List<Part> parts;

        public Content(String prompt) {
            this.parts = List.of(new Part(prompt));
        }

        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }
    }

    public static class Part {
        private String text;

        public Part(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
