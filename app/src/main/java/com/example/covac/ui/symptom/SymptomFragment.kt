package com.example.covac.ui.symptom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covac.R
import com.example.covac.adapter.SymptomAdapter
import com.example.covac.data.Symptom
import com.example.covac.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_symptom.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SymptomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SymptomFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_symptom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        symptom_layout.setOnClickListener{
            startActivity(Intent(context, SymptomInfoActivity::class.java))
        }

        new_post_btn.setOnClickListener {
            startActivity(Intent(context, RegisterSymptomActivity::class.java))
        }

        st_button.setOnClickListener {
            startActivity(Intent(context, SetFilterActivity::class.java))
        }


        val symptomList = arrayListOf<Symptom>()
        symptomList.add(Symptom("비누", "아스카라제네카", "20대", "서울"
        ,"2020-01-01", "당일과 다음날은 무조건 쉬어야 할것 같네요.", false, true, false, true, true))

        symptomList.add(Symptom("이고백", "화이자", "50대", "경기"
            ,"2020-01-03", "온몸에 몸살이나서 주말동안 아무것도 못했습니다ㅠㅠ", true, false, false, false, true))


        symptomList.add(Symptom("태은", "화이자", "20대", "서울"
            ,"2021-01-05", "울렁거리는 느낌이 들었습니다.", false, true, true, false, false))


        recycler_view.apply{
            layoutManager = LinearLayoutManager(context)
            adapter =
                SymptomAdapter(symptomList) { symptom ->
                    Toast.makeText(context, "$symptom", Toast.LENGTH_SHORT).show()
                }
        }


    }

}