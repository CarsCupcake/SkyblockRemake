package me.CarsCupcake.SkyblockRemake.hypixelApi;

import me.CarsCupcake.SkyblockRemake.utils.Factory;

public interface RequestArgument<T> {
    String toJsonValue(T t);
    T fromJsonValue(String s);
    boolean valid(String s);
    RequestArgument<String> STRING = new RequestArgument<>() {
        @Override
        public String toJsonValue(String s) {
            return "\"" + s + "\"";
        }

        @Override
        public String fromJsonValue(String s) {
            return s.substring(1, s.length() - 1);
        }

        @Override
        public boolean valid(String s) {
            return (s.startsWith("\"") && s.endsWith("\"")) || (s.startsWith("'") || s.endsWith("'"));
        }
    };
    RequestArgument<Integer> INTEGER = new RequestArgument<>() {
        @Override
        public String toJsonValue(Integer s) {
            return s + "";
        }

        @Override
        public Integer fromJsonValue(String s) {
            return Integer.parseInt(s);
        }
        static final char[] DIGITS = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        private boolean validate(char c) {
            for (char d : DIGITS) {
                if (d == c) return true;
            }
            return false;
        }
        @Override
        public boolean valid(String s) {
            for (char c : s.toCharArray()) {
                if (!validate(c)) return false;
            }
            return true;
        }
    };
}
