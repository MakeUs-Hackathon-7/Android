package com.example.covac.ui.talk

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covac.R
import com.example.covac.adapter.TalkAdapter
import com.example.covac.data.Talk
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk.recycler_view

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TalkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TalkFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_talk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        new_talk_btn.setOnClickListener {
            startActivity(Intent(context, UploadTalkActivity::class.java))
        }


        val talkList = arrayListOf<Talk>()
        talkList.add(Talk("비누", "2020-03-08", "댓글 1개", "백신 다들 언제쯤 맞으시나요?"))

        talkList.add(Talk("이고백", "2020-03-09", "댓글 3개", "제 지인은 간호사인데 벌써 백신 맞았다구 하네요!"))

        talkList.add(Talk("태은", "2020-03-10", "댓글 5개", "저 엊그제 백신 맞았는데 휴가냈어요ㅠㅠ"))

        talkList.add(Talk("헤더", "2020-03-10", "댓글 3개", "백신 맞기전에 삼겹살 먹으면 안아프다는거 실환가요?"))

        talkList.add(Talk("lofi", "2020-03-10", "댓글 7개", "아스트라제네카 vs 화이자 ㄱ"))


        recycler_view.apply{
            layoutManager = LinearLayoutManager(context)
            adapter =
                   TalkAdapter(talkList) { talk ->
                        Toast.makeText(context, "$talk", Toast.LENGTH_SHORT).show()
                       startActivity(Intent(context, TalkDetailActivity::class.java))
//                    startActivity(Intent(this, Symptom))
                    }
        }
    }
}