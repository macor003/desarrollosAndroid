package io.futurestud.retrofit1.api.model;

/**
 * Created by norman on 12/26/16.
 */

public class GitHubRepo {

    private String name;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
