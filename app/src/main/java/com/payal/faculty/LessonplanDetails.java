package com.payal.faculty;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.payal.faculty.R;


import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class LessonplanDetails extends AppCompatActivity{

    DatabaseHelper myDB;
    TextView txtId,txtData;
    ImageView photo;
    String strData="";
    int uid=0;
    Button btnPrint;
    String strFname="",strPass="",strDate="",strTime="",strDept="",strYear="",strSub="",strPract="",strPractNo="",strPractCheck="",strLect="", strNote="",strPointCov="",strAssignQues="";
    byte[] blob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlessonplana);

        txtId=(TextView)findViewById(R.id.textID);
        photo=(ImageView)findViewById(R.id.imageView3);
        txtData=(TextView)findViewById(R.id.textData);
        btnPrint=(Button)findViewById(R.id.btnPrint);

        myDB=new DatabaseHelper(this);

        Intent in=getIntent();
        int user_id=in.getIntExtra("U_ID1",0);
        String strD=in.getStringExtra("DATE");
        String strT=in.getStringExtra("TIME");

        Cursor c=myDB.getAllBData(""+user_id,strD,strT);
        while (c.moveToNext()) {

            uid=c.getInt(0);

            blob = c.getBlob(1);

            strData="\n\n Faculty Name: \t\t\t\t"+c.getString(3);
            strFname=c.getString(3);

            strData+="\n\n Password: \t\t\t\t\t\t"+c.getString(2);
            strPass=c.getString(2);

            strData+="\n\n Date: \t\t\t\t\t\t\t"+c.getString(7);
            strDate=c.getString(7);

            strData+="\n\n Time: \t\t\t\t\t\t\t"+c.getString(8);
            strTime=c.getString(8);

            strData+="\n\n Department: \t\t\t\t\t"+c.getString(4);
            strDept=c.getString(4);

            strData+="\n\n Year: \t\t\t\t\t\t\t"+c.getString(5);
            strYear=c.getString(5);

            strData+="\n\n Subject: \t\t\t\t\t\t"+c.getString(6);
            strSub=c.getString(6);

            strData+="\n\n Practical: \t\t\t\t\t\t"+c.getString(9);
            strPract=c.getString(9);

            strData+="\n\n Practical No: \t\t\t\t\t"+c.getString(10);
            strPractNo=c.getString(10);

            strData+="\n\n Practical Checked: \t\t\t"+c.getString(11);
            strPractCheck=c.getString(11);

            strData+="\n\n Lecture: \t\t\t\t\t\t"+c.getString(12);
            strLect=c.getString(12);

            strData+="\n\n Notes: \t\t\t\t\t\t\t"+c.getString(13);
            strNote=c.getString(13);

            strData+="\n\n Points Covered: \t\t\t\t"+c.getString(14);
            strPointCov=c.getString(14);

            strData+="\n\n Assignment Questions:\t"+c.getString(15);
            strAssignQues=c.getString(15);

            strData+="\n\n";

            txtId.setText("" + uid);

            photo.post(new Runnable() {
                @Override
                public void run() {

                    photo.setImageBitmap(Utility.getImage(blob));

                }
            });

            txtData.setText(strData);
        }

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        btnPrint.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                createPDFFile(Common.getAppPath(getApplicationContext())+"test_pdf.pdf");
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    }
                })
                .check();
    }
    private void createPDFFile(String path)
    {
        if (new File(path).exists())
            new File(path).delete();
        try {
            Document document= new Document();
            //save
            PdfWriter.getInstance(document,new FileOutputStream(path));
            //Open to write
            document.open();

            //setting
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("Admin");
            document.addCreator("Admin");

            //Font setting
            BaseColor colorAccent= new BaseColor(0,153,204,255);
            float fontSize=25.0f;
            float valueFontSize= 26.0f;

            //custom font
            BaseFont fontName = BaseFont.createFont("assets/fonts/times_roman.ttf", "UTF-8", BaseFont.EMBEDDED);

            //Create title of document
            Font titleFont= new Font(fontName,36.0f,Font.BOLD,new BaseColor(0,0,102));
            addNewItem(document,"Faculty Details", Element.ALIGN_CENTER,titleFont);

            //add more
            Font orderNumberFont =new Font(fontName,fontSize,Font.NORMAL,BaseColor.BLACK);


            Font orderNumberValueFont =new Font(fontName,fontSize,Font.NORMAL,BaseColor.BLACK);

            addLineSeperator(document);
            addNewItemWithLeftAndRight(document,"User ID:",""+uid, Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);

            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Photo:","", Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);
            addImage(document);


            addNewItemWithLeftAndRight(document,"","", Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);
            addNewItemWithLeftAndRight(document,"","", Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);
            addNewItemWithLeftAndRight(document,"","", Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);
            addNewItemWithLeftAndRight(document,"","", Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);

            addLineSeperator(document);
            addNewItemWithLeftAndRight(document,"Faculty Name:", strFname, Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);

            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Password:",strPass,Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);

            addLineSeperator(document);

            addNewItem(document,"Lesson Plan Details", Element.ALIGN_CENTER,titleFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Date:",strDate,Element.ALIGN_CENTER,orderNumberFont,orderNumberValueFont);

            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Time:",strTime, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);

            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Department:",strDept, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Year:",strYear, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Subject:",strSub, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Practical:",strPract, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Practical No:",strPractNo, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Practical Checked:",strPractCheck, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Lecture:",strLect, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Notes:",strNote, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Points Covered:",strPointCov, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRight(document,"Assignment Questions:",strAssignQues, Element.ALIGN_CENTER, orderNumberFont,orderNumberValueFont);
            addLineSeperator(document);
            document.close();

            printPDF();
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (DocumentException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addImage(Document document) {
        try {
            Rectangle rectDoc=document.getPageSize();
            float width=rectDoc.getWidth();
            float height=rectDoc.getHeight();
            float imgStartX=width-document.rightMargin()-280f;
            float imgStartY=height-document.topMargin()-310f;

            System.gc();
            Image img=Image.getInstance(blob);
            img.setAlignment(Image.TEXTWRAP);
            img.scaleAbsolute(170f,170f);
            img.setAbsolutePosition(imgStartX,imgStartY);
            document.add(img);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printPDF() {
        PrintManager printManager=(PrintManager)getSystemService(Context.PRINT_SERVICE);
        try
        {
            PrintDocumentAdapter printDocumentAdapter=new PdfDocumentAdapter(this,Common.getAppPath(this)+"test_pdf.pdf");
            printManager.print("Document",printDocumentAdapter, new PrintAttributes.Builder().build());
        }
        catch (Exception ex)
        {
            Log.e("Lesson_Plan",""+ex.getMessage());
        }
    }

    private void addNewItemWithLeftAndRight(Document document, String textLeft, String textRight,int align, Font textLeftFont, Font textRightFont) throws DocumentException {
        Chunk chunkTextLeft=new Chunk(textLeft,textLeftFont);

        Chunk chunkTextRight=new Chunk(textRight,textRightFont);
        Paragraph p=new Paragraph(chunkTextLeft);
        p.setAlignment(align);
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(chunkTextRight);
        document.add(p);
    }

    private void addLineSeperator(Document document) throws DocumentException {
        LineSeparator lineSeparator=new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,0,68));
        addLineSpace(document);
        document.add(new Chunk(lineSeparator));
        addLineSpace(document);
    }

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(""));
    }

    private void addNewItem(Document document, String text, int align, Font font) throws DocumentException {
        Chunk chunk=new Chunk(text,font);
        Paragraph paragraph=new Paragraph(chunk);
        paragraph.setAlignment(align);
        document.add(paragraph);
    }
}
