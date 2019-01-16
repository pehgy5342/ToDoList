package com.example.pehgy.todolist

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.dialog_custom.view.*

class MainActivity : AppCompatActivity() {
    val data = ArrayList<Info>()
    lateinit var myadapter: MainAdapter
    lateinit var mysp: MyPreference
    val deleteList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mysp = MyPreference(this)
        myadapter = MainAdapter(this, data, this@MainActivity::addDeleteList)



        data.addAll(mysp.readit())//開啟後顯示上次資料


        fab_add.setOnClickListener {


            val dialog = AlertDialog.Builder(this@MainActivity)
            val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)
            dialog.setView(dialogView)


            dialog.setTitle("新增代辦事項")

            dialog.setPositiveButton("新增") { dialog, which ->
                val etinput = dialogView.et_input.text.toString()

                if (etinput.length > 0) {

                    mysp.save(input = etinput)
                    myadapter.updateList( mysp.readit())
                    // tv_show.text = data


                    // datalist.add(Info(etinput)) //簡單的加入list
//                    val newList = arrayListOf<String>()
//                    newList.add(etinput)
//                    println("****${newList}")

                    //myadapter.updateList(newList)
                    //myadapter.infoList.add()//加入更多string在infoList

                } else {
                    Toast.makeText(this, "請輸入文字", Toast.LENGTH_SHORT).show()
                }

            }

            dialog.setNegativeButton("取消") { dialog, which ->
                Toast.makeText(this, "已取消", Toast.LENGTH_SHORT).show()

            }
            dialog.show()

        }


        //        fun RemoveInfo() {
//
//
//            myadapter.getDeleteList().forEach { sharedPreferences.edit().remove(it).apply() }
//
//
//        }


        //刪除項目
        btn_delete.setOnClickListener {

            mysp.RemoveInfo(RemoveList = deleteList)
            myadapter.updateList( mysp.readit())
            println("**${mysp.RemoveInfo(RemoveList = deleteList)}")

        }

        //讀取字串
        btn_readInfo.setOnClickListener {
            println("****${mysp.readit()}")
        }


        //開啟APP後顯示

        recyclerView.adapter = myadapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)


    }

    fun addDeleteList(key: String, isCheck: Boolean) {

        if (isCheck) {

            deleteList.add(key)

        } else {

            deleteList.remove(key)
        }

    }


//    fun D(a: String) {}
//
//    fun A(action: (String, Int) -> Unit) {
//        val a = 1
//        action.invoke("a", 0)
//    }
//
//    fun B() {
//        A(::C)
//    }
//
//    fun C(a: String, b: Int) {
//
//    }


}


//利用fun的方式
//    fun todolist(): MutableList<Info> {
//        val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)
//        val input = dialogView.et_input.text.toString()
//        var data = mutableListOf<Info>()
//        data.add(Info(input))
//
//        return data
//    }


