package com.challenge.bossaboxChallenge.entitiy;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_tools")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String link;

    private String description;

    @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL)
    private List<ToolTag> toolTags;

    public Tool() {}

    public Tool(String title, String link, String description, List<ToolTag> toolTags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.toolTags = toolTags;
    }

    public Tool(Long id, String title, String link, String description, List<ToolTag> toolTags) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.toolTags = toolTags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ToolTag> getToolTags() {
        return toolTags;
    }

    public void setToolTags(List<ToolTag> toolTags) {
        this.toolTags = toolTags;
    }
}
