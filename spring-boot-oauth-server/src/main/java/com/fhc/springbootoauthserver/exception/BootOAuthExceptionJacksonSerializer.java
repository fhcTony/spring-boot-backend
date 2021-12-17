package com.fhc.springbootoauthserver.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

/**
 * @author fuhongchao
 * @create 2020/5/18 13:16
 */
public class BootOAuthExceptionJacksonSerializer extends StdSerializer<BootOAuth2Exception> {

    protected BootOAuthExceptionJacksonSerializer() {
        super(BootOAuth2Exception.class);
    }

    @Override
    public void serialize(BootOAuth2Exception value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("code", value.getHttpErrorCode());
        jsonGenerator.writeStringField("msg", value.getMessage());
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jsonGenerator.writeStringField(key, add);
            }
        }
        jsonGenerator.writeEndObject();
    }
}
