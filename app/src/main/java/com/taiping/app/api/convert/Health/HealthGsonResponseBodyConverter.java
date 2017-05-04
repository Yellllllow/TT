package com.taiping.app.api.convert.Health;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.taiping.app.api.exception.ApiException;
import com.taiping.app.model.HealthResult;
import com.taiping.app.model.response.wx.WXResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by God on 2016/8/20.
 */
public class HealthGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Gson mGson;
    private final TypeAdapter<T> adapter;

    public HealthGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        mGson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        HealthResult re = mGson.fromJson(response, HealthResult.class);
        re.setCode(200);
        response=new Gson().toJson(re);
        if (!re.isOk()) {
            value.close();
            if (re.getTngou()!= null) {
                String errorExtendMsg = mGson.toJson(re.getTngou());
                throw new ApiException(re.getCode(), "", errorExtendMsg);
            } else {
                throw new ApiException(re.getCode(), "");
            }
        }

        MediaType mediaType = value.contentType();
        Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
        ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes());
        InputStreamReader reader = new InputStreamReader(bis, charset);
        JsonReader jsonReader = mGson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
