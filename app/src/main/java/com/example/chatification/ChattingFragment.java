package com.example.chatification;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ChattingFragment extends Fragment {

    private MenuListAdapter mlistAdapter;
    private RecyclerView recyclerView;
    private ArrayList<ListItem> arrayList = new ArrayList<>();
    private DatabaseReference dbReference;

    private String id;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.chatting_fragment, container, false);

        id = getArguments().getString("u_id");
        recyclerView = rootView.findViewById(R.id.recyclerView);

        new CrawlingTask().execute();

        return rootView;
    }

    private class CrawlingTask extends AsyncTask <Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            dbReference = FirebaseDatabase.getInstance().getReference().getRoot();

            try {

                SSLConnect ssl = new SSLConnect(); // SSL 인증서 오류 해결하기 위한 클래스, 정확히 어떤 일을 하는 지는 잘 모르겠다.
                ssl.postHttps("https://jobs.gnu.ac.kr/user/Pg/PgPg010L.do?CURRENT_MENU_CODE=MENU2017&TOP_MENU_CODE=MENU2016", 1000, 1000);

                Document doc = Jsoup.connect("https://jobs.gnu.ac.kr/user/Pg/PgPg010L.do?CURRENT_MENU_CODE=MENU2017&TOP_MENU_CODE=MENU2016").get();
                Elements elements = doc.select("div[class=tableList] tbody").select("tr");

                for(Element elem : elements) { // elements에서 하나씩 꺼내서 elem에 저장
                    String title = elem.select("th[class=ellipsis] a").text();
                    String tdText = elem.select("td").text(); // 이건 아닐 수 있다

                    String[] textSplited = tdText.split(" ");
                    String endDate = "접수 마감 : " + textSplited[3] + " " + textSplited[4]; // 연.월.일 + 시간대
                    String isAccepting = textSplited[9]; // 접수중 or 마감

                    boolean status = false;
                    if (isAccepting.equals("접수중")) status = true;

                    // Log.e(title, tdText);

                    arrayList.add(new ListItem(title, endDate, status));

                    dbReference.child(title).push(); // 아닐 수도
                }
            }
            catch (IOException e) {
                Log.e("abd", "" + e);
                //e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute (Void result) {
            mlistAdapter = new MenuListAdapter(arrayList, id);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            recyclerView.setAdapter(mlistAdapter);

        }
    }
}
