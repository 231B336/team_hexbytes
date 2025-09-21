package com.example.sih

import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import android.widget.Toast
import android.content.Context

object PDFGenerator {

    fun generateStudentPortfolio(context: Context, activities: List<Map<String, Any>>) {
        try {
            // 1️⃣ Generate PDF
            val pdfFile = File(Environment.getExternalStorageDirectory(), "StudentPortfolio.pdf")
            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas
            val paint = Paint()
            paint.textSize = 16f

            var y = 50
            canvas.drawText("Student Portfolio", 200f, y.toFloat(), paint)
            y += 30

            activities.forEach { activity ->
                canvas.drawText(
                    "${activity["activityName"]} - ${activity["category"]} - Credits: ${activity["credits"]}",
                    50f,
                    y.toFloat(),
                    paint
                )
                y += 25
            }

            document.finishPage(page)

            // 2️⃣ Save PDF to storage
            document.writeTo(FileOutputStream(pdfFile))
            document.close()

            Toast.makeText(context, "PDF saved at: ${pdfFile.path}", Toast.LENGTH_LONG).show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to generate PDF: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
