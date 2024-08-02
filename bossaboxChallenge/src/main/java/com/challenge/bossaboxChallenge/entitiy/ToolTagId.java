package com.challenge.bossaboxChallenge.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ToolTagId {

    @Column(name = "tool_id")
    private Long toolId;

    @Column(name = "tag_id")
    private Long tagId;

    public ToolTagId() {}

    public ToolTagId(Long toolId, Long tagId) {
        this.toolId = toolId;
        this.tagId = tagId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
