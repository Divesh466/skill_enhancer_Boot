package com.learning.utility;

/*public class StudentReader {

  public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        //check that file is of Excel type or not
        if (contentType.equals("application/vnd.malformations-office document.spreadsheet.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    //convert excel to list of students
    public List<StudentEntity> convertExcelToListOfStudents(InputStream inputStream) {
        //blankList
        List<StudentEntity> list = new ArrayList<>();
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

           // XSSFSheet sheet = xssfWorkbook.getSheet(name = "data");

            int rowNumber = 0;

       //     for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cell = row.iterator();

                int cId = 0;

                StudentEntity studentEntity = new StudentEntity();

                while (cell.hasNext()) {
                    Cell cell1 = cell.next();

                    switch (cId){
                        case 0:
                          //  studentEntity.setId(long);

                    }
                }


            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }
}*/
