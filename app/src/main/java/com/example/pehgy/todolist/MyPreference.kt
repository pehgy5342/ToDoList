package com.example.pehgy.todolist

import android.content.Context
import android.content.SharedPreferences

class  MyPreference(var context: Context) {
    var keysSize = 0 //key的數量，有幾個key
    var storeCount = 1 //key的增加
    var sharedPreferences: SharedPreferences
    //在adapter中只做畫面，這裡做計算，所以不要將東西拿來這裡
    //var myadapter: MainAdapter


    init {
        sharedPreferences = context.getSharedPreferences("List", Context.MODE_PRIVATE)

        keysSize = sharedPreferences.all.keys.size
        if (keysSize > 0) {

            var keyList = ArrayList<Int>()//建立空陣列裡面放所有的key
            sharedPreferences.all.keys.forEach { keyList.add(it.toInt()) }//將原本為String的key轉為Int做計算，並加入空陣列中
            storeCount = keyList.max()!! + 1 //增加key的數量
        }
    }


    fun save(input: String) {

        //       val deleteList = arrayListOf<String>()

//        for (i in 1..getKeyMax()) {
//
//            val msg = sharedPreferences.edit().putString(i.toString(), input).apply()
//            deleteList.add(msg.toString())
//        }
       //因為最大值key預設為0，所以將其+1才能抓到其值
        val newKey = getKeyMax() + 1
        sharedPreferences.edit().putString(newKey.toString(), input).apply()

        //sharedPreferences.edit().putString("word", input).apply()
    }

    fun readit(): ArrayList<Info> {

        val dataArrayList = arrayListOf<Info>()//建立讓輸出能夠順序排列的arraylist

        for (i in 1..getKeyMax()) {

            val output = sharedPreferences.getString(i.toString(), "")
            if (output != "") {
                dataArrayList.add(Info(i.toString(), output))
            }


        }
        return dataArrayList
        //  return sharedPreferences.getString("word","0")!!
    }


    fun RemoveInfo(RemoveList:ArrayList<String>) {


       RemoveList.forEach { sharedPreferences.edit().remove(it).apply() }


    }


    //獲取key的最大值
    fun getKeyMax(): Int {
        val max = sharedPreferences.all.keys.map { x -> x.toInt() }.max()
        val trueMax = if (max == null) 0 else max.toInt()

        return trueMax
    }


}







