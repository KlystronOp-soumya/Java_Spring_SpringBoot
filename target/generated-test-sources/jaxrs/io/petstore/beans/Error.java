
package io.petstore.beans;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "message"
})
@Generated("jsonschema2pojo")
public class Error {

    /**
     * A unique error code.
     * (Required)
     * 
     */
    @JsonProperty("code")
    @JsonPropertyDescription("A unique error code.")
    private String code;
    /**
     * A human-readable error message.
     * (Required)
     * 
     */
    @JsonProperty("message")
    @JsonPropertyDescription("A human-readable error message.")
    private String message;

    /**
     * A unique error code.
     * (Required)
     * 
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     * A unique error code.
     * (Required)
     * 
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * A human-readable error message.
     * (Required)
     * 
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * A human-readable error message.
     * (Required)
     * 
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

}
