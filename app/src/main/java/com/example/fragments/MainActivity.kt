package com.example.fragments

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.Manifest.permission
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    val nombreArchivoAI = "miarchivo.txt"
    val datosAI = "Contenido del archivo en almacenamiento interno"
    val nombreArchivoAE = "miarchivoE.txt"
    val datosAE = "Contenido del archivo en almacenamiento externo"
    val clave = "Clave"
    val valor ="Mi valor de cache"
    val databaseHelper=DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val fragment = PruebaFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmetContainer, fragment).commit()
        */
        /*
        val viewPager: ViewPager = findViewById(R.id.vp1)
        val adapter = viewPageAdapter(supportFragmentManager)
        viewPager.adapter = adapter*/
        //requestCameraPermission()

        //escribirDatosAlmacenamientoInterno(nombreArchivoAI, datosAI)
        //escribirDatosAlmacenamientoExterno()
        escribirDatosAlmacenamientoCache(this,clave,valor)
    }

    override fun onResume() {
        super.onResume()
        //val contenido = leerDatosAlmacenamientoExterno()
        //Toast.makeText(this,contenido,Toast.LENGTH_LONG).show()
        val contenido = leerDatosAlmacenamientoCache(this,clave)
        Toast.makeText(this,contenido,Toast.LENGTH_LONG).show()
    }

    fun escribirDatosAlmacenamientoInterno(nombreArchivo: String, datos: String){
        val archivo = File(this.filesDir,nombreArchivo)
        archivo.writeText(datos)
    }
    fun leerDatosAlmacenamientoInterno(nombreArchivo: String):String{
        val archivo = File(this.filesDir, nombreArchivo)
        return archivo.readText()
    }

    private fun escribirDatosAlmacenamientoExterno(){
        val estado = isExternalStorageWritable()
        if (estado){
            val directorio = getExternalFilesDir(null)
            val archivo = File(directorio, nombreArchivoAE)
            try {
                FileOutputStream(archivo).use{
                    it.write(datosAE.toByteArray())
                }
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun leerDatosAlmacenamientoExterno():String{
        val estado = isExternalStorageReadable()
        if (estado){
            val directorio = getExternalFilesDir(null)
            val archivo = File(directorio, nombreArchivoAE)
            val fileInputStream = FileInputStream(archivo)
            val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({text = bufferReader.readLine();text}()!=null){
                stringBuilder.append(text)
            }
            fileInputStream.close()
            return stringBuilder.toString()
        }
        return ""
    }

    private fun isExternalStorageWritable():Boolean{
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == estado
    }

    private fun isExternalStorageReadable():Boolean{
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED==estado||Environment.MEDIA_MOUNTED_READ_ONLY==estado
    }

    fun escribirDatosAlmacenamientoCache(context: Context, clave: String, valor:String){
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(clave, valor)
        editor.apply()
    }

    fun leerDatosAlmacenamientoCache(context: Context, clave:String):String?{
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        return sharedPreferences.getString(clave,null)
    }


    /*
    private fun requestCameraPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){

        }else{
            ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode){
            CAMERA_PERMISSION_CODE->{
                if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else{

                }
            }
        }
    }
     */
}