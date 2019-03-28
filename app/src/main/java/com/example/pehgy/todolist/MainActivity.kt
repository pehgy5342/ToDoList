package com.example.pehgy.todolist

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.dialog_custom.view.*
import kotlinx.android.synthetic.main.recycler_view.*

class MainActivity : AppCompatActivity() {
    val data = ArrayList<Info>()
    lateinit var myadapter: MainAdapter
    lateinit var mysp: MyPreference
    val deleteList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById<View>(R.id.tool_bar) as Toolbar

        setSupportActionBar(toolBar)


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
                    //可在dialog_custom裡改變輸入字串的長度，maxLength = 字數(中文、數字和英文都相同)
                    mysp.save(input = etinput)
                    myadapter.updateList(mysp.readit())
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

//        btn_trash.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("警告")
//            builder.setMessage("確定要刪除嗎??")
//            builder.setPositiveButton("Yes", { dialogInterface: DialogInterface, i: Int -> })
//            builder.setNegativeButton("no", { dialogInterface: DialogInterface, i: Int -> })
//            builder.show()
//
//        }


        //刪除項目
        btn_delete.setOnClickListener {
            choice.visibility = View.VISIBLE
            mysp.RemoveInfo(RemoveList = deleteList)
            myadapter.updateList(mysp.readit())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
   //     val click = R.id.choice

        when (id) {

            R.id.check ->  Toast.makeText(this,"123",Toast.LENGTH_SHORT).show()


        }

        return super.onOptionsItemSelected(item)
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


