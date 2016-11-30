package org.suliga.trantor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Greeting {

    private long id;
    private String content;

    public Greeting() {}
    
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The id or magic number of the user", required = true)
    public long getId() {
        return id;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The name of the user", required = true)
    public String getContent() {
        return content;
    }
}
