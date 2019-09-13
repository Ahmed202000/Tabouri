package com.example.ahmedhegazy.tabouri;

import java.util.ArrayList;
import android.content.Context;
        import android.widget.Toast;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.List;

/**
 * Created by RetalPc on 02/21/2019.
 */

public class GietBanLogo {

    public List<TabouriBanLogo> GetDate(Context cn)
    {

        List<TabouriBanLogo> data=new ArrayList<>();
        Database db=new Database();
        Connection conn=db.ConnectDB();
        if(conn==null)
            Toast.makeText(cn, "عفوا رجاء الاتصال با الانترنت", Toast.LENGTH_SHORT).show();
        else
        {
            ResultSet m=db.RunSearch("select * from Banks");
            try {
                while (m.next())
                {
                    TabouriBanLogo s=new TabouriBanLogo();
                    s.setBankNO(m.getString(1));
                    s.setBankName(m.getString(2));
                    s.setBankEmail(m.getString(3));
                    s.setBankPhone(m.getString(4));
                    s.setBankDetails(m.getString(5));
                    s.setBankLogo(m.getString(6));
                    data.add(s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return data;
    }

}

