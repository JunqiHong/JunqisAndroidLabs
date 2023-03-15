package algonquin.cst2335.ID041012545;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import algonquin.cst2335.ID041012545.data.ChatMessage;

@Database(entities = {ChatMessage.class}, version=1)
public abstract class MessageDatabase extends RoomDatabase {

    public abstract ChatMessageDAO cmDAO();

}
