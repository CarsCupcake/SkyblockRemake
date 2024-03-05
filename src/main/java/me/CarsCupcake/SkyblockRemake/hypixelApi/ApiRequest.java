package me.CarsCupcake.SkyblockRemake.hypixelApi;

import java.util.HashMap;

/**
 *
 * @param <T> Api Response Type
 */
public interface ApiRequest<T extends RequestArgument<?>> {
    void rquest();
    default T packageRequest(HashMap<String, RequestArgument<?>> requestArgs) {
        return null;
    }
}
