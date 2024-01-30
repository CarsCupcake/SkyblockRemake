package me.CarsCupcake.SkyblockBungee.features;

import lombok.Getter;
import org.jetbrains.annotations.Contract;

public enum ServerType {

    Hub("hub", 25565, "hub", "https://download1327.mediafire.com/pu25tgffejlgBZFIqSE_QKFKgnhUuJg0oNkl4WP69ZLhLP8PO-v0y2MM_jy3hl3hDJdvGjwPySNp9ui8RZeh0hXXOl0b7vW2ONV7KaiowRAsvLn4E_QNrYVnAX_pSd3P6exaLPCLdjt1v8rgLztWMYZVedz5gMZKhJ_0U0HpEs9z7p8/gqtcg573rctpba0/hub.zip"),
    Non("", 0, "non"),
    DwarvenMines("dwarven", 25564, "mines", "https://download1077.mediafire.com/uf9nsmcy5obgMWhpGuWMnnsWn3I010pX0_6q88j02hsyTFY2aolRcA2JU1PjyJGaxBqyb671n_K764nPxnJUvZLk9OFVop56xBJOOWh5-OugbXEOiKD6dw2jISwwmUzJrztGI9hwUj-5spCKzooSV7Cv-4yGqYSvL-1v_I6tma26kgw/wi0cx6tu3tnhj0h/DwarvenMines.zip"),
    CrimsonIsle("crimsonisle", 25568, "nether", "https://download1523.mediafire.com/ph7knvdimhogN-jflNOgHjWUERiOREYjnYTFEyL4CTd_7g7HX2hu-pOFou6oXMagWGgll7flUPZ_dtr-a-VMNSIHknNhRboPulw4y_QOByI2PU9P8diuJ6a7rjVv3f79wUuQW4O5cGp5s5RUB6gPssbtnyozuZZ9Bg32TIchZ6Z8yNQ/uix7mk88qxq92e7/CrimsonIsle.zip"),
    TheInstance("kuudra", 25569, "kuudra"),
    DungeonHub("dh", 25570, "dungeon_hub"),
    F1("f1", 25567, "f1", "https://download1652.mediafire.com/i4i68t2ium8gCobWVD98nnfprNmXL1YrbuvE0FOQjtuTbnzPBCiKD64ZXIXCeqHVgAG_6ySCZFPVBMMjzQoBpGv3QC4lp5vqlGu2AKE8_YQZvW9ELQMiw2BLG3m2NE2B-2Wzre-O7DTfvwvmHJZ0oMF7_3oWEpPf-73m8MsCzi_FSMU/1mnpq0zc3g6f6dm/f1.zip"),
    F7("f7", 25567, "f7", "https://download1327.mediafire.com/i4brrhwsrt3gmdgU6jbkQeSSmVh7oj1IX7Luacrlr7SUzaeSJeWsVqWQPjdn4CYKPBYkXQrl2lq4JircoSebbWYqGHUhB6J6TFvpTO6fcObWziu8QYowDOrrf-uv7PqYfIOzQFWVbW0SXThq3Sc1I7XVx4juXzY0zy29VllUs2KD3Ts/kmyj2ee1zlz88z3/f7.rar"),
    F6("f6",25572, "f6", "https://download1077.mediafire.com/5qur79nvbibgRvPXCNrucvWQYWTWnSGiXQA1tldpC58crwim8qyo4outg6MytArd4GlGuZu6tZG4zTaIDj_iGyZsoachUuA6b8VqFERbw6Lt1Hi6lL3NDHAa1ihCqVAE7aX4W1DRSYvgynfm-RTXTH14T7quoSc_Uo2LFlSoQ4mGB_Q/qz14snmrel1o2o5/f6.zip"),
    SpidersDen("spidersden",25573,  "spider"),
    End("EndIsle", 25571, "end", "https://download1320.mediafire.com/fkfxajk2fw5gQkBYjIiMOno4bYj2XUah8GZ-n_Vd-sRIFhLOtBoXy5gpdQoVjkNy8xKgIee5b_Mmi-XY3oZs5erlDfnjIFSjADj5gniLep85gVzEIjLsPsUtvE4_EAoIy-oIgJ899eIlEsvmi9BlppFZUDl-jjpUxn-D19amIvLaIMY/ekkbqgl0ijeq1uv/EndIsle.zip"),
    //Standart Loc = 7.5, 100, 7.5
    PrivateIsle("private", 25574, "private_isle"),
    F3("f3", 255575, "f3"),
    Dungeon("dungeon", 25576, "dungeon"),
    Rift("rift", 25575, "rift", "https://download1593.mediafire.com/v82ox8x5w6hge2JbDQuZBP4mAJoGvrcS6JLnZHJpYY8cUDYStElAvOTKsxd8bqL8668GcCZxLhjOOZeDuB_qZ40Zqa_Cmiosr4C913DiRzksmMjlWeg7fq_cncw1-fRDneTrBwCxxY08z2h1TpEx3jzoA2ncREAnTtoURcWAE18mAho/pdznua1y1fnw1a3/RiftIlse.zip");
    public final String s;
    public final int ip;
    @Getter
    private final String name;
    @Getter
    private final String url;
    ServerType(String s, int i, String name){
        this(s, i, name, null);
    }
    ServerType(String s, int i, String name, String download){
        this.s = s;
        ip = i;
        this.name = name;
        this.url = download;
    }

    @Contract(pure = true)
    public static ServerType getFromString(String s){
        ServerType type = Non;

        for (ServerType types : ServerType.values())
            if(types.s.equalsIgnoreCase(s))
                type = types;

        return type;
    }

    @Contract(pure = true)
    public static ServerType fromName(String s){
        ServerType type = Non;

        for (ServerType types : ServerType.values())
            if(types.s.equalsIgnoreCase(s))
                type = types;

        return type;
    }

}
