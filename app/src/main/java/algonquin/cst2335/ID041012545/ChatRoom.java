package algonquin.cst2335.ID041012545;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.ID041012545.data.ChatMessage;
import algonquin.cst2335.ID041012545.data.ChatViewModel;
import algonquin.cst2335.ID041012545.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.ID041012545.databinding.ReceiveMessageBinding;
import algonquin.cst2335.ID041012545.databinding.SentMessageBinding;


public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages;

    private RecyclerView.Adapter myAdapter;
    ChatViewModel chatModel;

    ChatMessageDAO mDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get a database
        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        ChatMessageDAO mDAO = db.cmDAO();


        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());

        chatModel = new ViewModelProvider(this).get(ChatViewModel.class);
        messages = chatModel.messages.getValue();

        if(messages == null)
        {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());
        }

        setContentView(binding.getRoot());

        binding.button.setOnClickListener(click->{
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateAndTime = sdf.format(new Date());
            ChatMessage chm= new ChatMessage(binding.textInput.getText().toString(), currentDateAndTime, true);
            messages.add(chm);

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(  () -> {
                    long id = mDAO.insertMessage(chm);
                    chm.id = (int) id;
            });
            //insert into database
            mDAO.insertMessage(chm);
            myAdapter.notifyItemInserted(messages.size()-1);
            //clear the previous text:
            binding.textInput.setText("");

        });
        binding.button1.setOnClickListener(click->{
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateAndTime = sdf.format(new Date());
            ChatMessage chm= new ChatMessage(binding.textInput.getText().toString(), currentDateAndTime, false);
            messages.add(chm);
            myAdapter.notifyItemInserted(messages.size()-1);
            //clear the previous text:
            binding.textInput.setText("");
        });

        binding.recyclerview.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if(viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }
                else
                {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }
            }
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                String obj = messages.get(position).getMessage();
                holder.messageText.setText(obj);
                holder.timeText.setText(messages.get(position).getTimeSent());
            }
            @Override
            public int getItemCount() {
                return messages.size();
            }
            @Override
            public int getItemViewType(int position)
            {
                if(messages.get(position).getIsSentButton())
                {
                    return 0;
                }
                else
                {
                    return 1;
                }
            }

        });

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    static class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText =itemView.findViewById(R.id.time);

        }
    }
}