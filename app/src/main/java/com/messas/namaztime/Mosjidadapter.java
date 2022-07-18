package com.messas.namaztime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class Mosjidadapter extends RecyclerView.Adapter<Mosjidadapter.myview> {
    public List<mosjidmodel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String classify;
    String dayarabic;
    public Mosjidadapter(List<mosjidmodel> data) {

        this.data = data;

    }

    @NonNull
    @Override
    public Mosjidadapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.maincard,parent,false);
        return new Mosjidadapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mosjidadapter.myview holder, final int position) {
        Calendar date = Calendar.getInstance();
        String dayToday = android.text.format.DateFormat.format("EEEE", date).toString();
        if (dayToday.toLowerCase().toString().equals("monday")) {
            dayarabic="الإثْنَي ";
        }
        else   if (dayToday.toLowerCase().toString().equals("tuesday")) {
            dayarabic="الثَلاثا ";
        }
        else   if (dayToday.toLowerCase().toString().equals("wednesday")) {
            dayarabic="الأربَعا ";
        }
        else   if (dayToday.toLowerCase().toString().equals("thursday")) {
            dayarabic="الخَمي ";
        }
        else   if (dayToday.toLowerCase().toString().equals("friday")) {
            dayarabic="الجُمُع ";
        }
        else   if (dayToday.toLowerCase().toString().equals("saturday")) {
            dayarabic="السَبْ ";
        }
        else   if (dayToday.toLowerCase().toString().equals("sunday")) {
            dayarabic="الأحَ ";
        }
        Calendar calendar = Calendar.getInstance();
        final int yearq = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH)+1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfMonth = day;
        int monthOfYear = month;
        int year = yearq;
// March 20th 2018
        LocalDate dt = LocalDate.of(year, monthOfYear, dayOfMonth);

// convert to hijrah
        HijrahDate hijrahDate = HijrahDate.from(dt);

// format to MM/DD/YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formatted = formatter.format(hijrahDate);
        //

        String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String current1 = DateFormat.getDateInstance().format(calendar.getTime());
        holder.customer_name.setText(data.get(position).getName()+"\n"+
              data.get(position).getLocation()+"\n" +
              "Today : "+dayToday+"( "+dayarabic+" )\n" +
              ""+current1+"\n" +
                ""+formatted+"");
        if (data.get(position).getSunset().toLowerCase().equals("not set")) {
            holder.customer_number10.setText("Fazar : " + data.get(position).getFazor() + " AM\n" +
                    "Sun Rise : " + data.get(position).getSunrise() + " AM\n" +
                    "Juhur : " + data.get(position).getJahor() + " PM\n" +
                    "Asr : " + data.get(position).getAsor() + " PM\n" +
                    "Magrib : " + data.get(position).getMagrib() + " PM\n" +
                    "Isha : " + data.get(position).getEsha() + " PM\n" +
                    "Jumma : " + data.get(position).getJumma() + " PM");
        }
        else {
            holder.customer_number10.setText("Fazar : " + data.get(position).getFazor() + " AM\n" +
                    "Sun Rise : " + data.get(position).getSunrise() + " AM\n" +
                    "Juhur : " + data.get(position).getJahor() + " PM\n" +
                    "Asr : " + data.get(position).getAsor() + " PM\n" +
                    "Magrib : " + data.get(position).getMagrib() + " PM\n" +
                    "Isha : " + data.get(position).getEsha() + " PM\n" +
                    "Jumma : " + data.get(position).getJumma() + " PM\n" +
                    "Notes : "+data.get(position).getSunset());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView customer_name,customer_number10,customer_area,logout,customer_area3,customer_area8,logout10;
        CardView card_view8;
        ImageView image;
        public myview(@NonNull View itemView) {
            super(itemView);
            customer_name=itemView.findViewById(R.id.customer_name);
            logout10=itemView.findViewById(R.id.logout10);
            customer_number10=itemView.findViewById(R.id.customer_number10);


        }
    }
}


