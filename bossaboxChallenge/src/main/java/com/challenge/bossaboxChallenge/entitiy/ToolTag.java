package com.challenge.bossaboxChallenge.entitiy;

import jakarta.persistence.*;

@Entity
@Table(name = "tool_tag")
public class ToolTag {

    @EmbeddedId
    private ToolTagId id;

    @ManyToOne
    @MapsId("toolId")
    @JoinColumn(name = "tool_id")
    private Tool tool;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public ToolTag() {}

    public ToolTag(ToolTagId id, Tool tool, Tag tag) {
        this.id = id;
        this.tool = tool;
        this.tag = tag;
    }

    public ToolTagId getId() {
        return id;
    }

    public void setId(ToolTagId id) {
        this.id = id;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
