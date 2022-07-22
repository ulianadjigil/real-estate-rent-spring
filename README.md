# real-estate-rent-spring
## Task
<img src="https://user-images.githubusercontent.com/85234616/121822292-ff03ab00-cca6-11eb-8157-d2be63ce5dd6.jpg" width="500">
Формула:
 <img src="https://user-images.githubusercontent.com/85234616/121822461-da5c0300-cca7-11eb-8dbf-b628b828f8c4.jpg" width="200">

## Solution
In **index.jsp** we can enter into the input only numbers with no more than 3 numbers after the decimal point. When user has sent filled form, action **TestServlet** starts.

<img width="690" alt="Снимок экрана 2021-06-14 в 00 58 21" src="https://user-images.githubusercontent.com/85234616/121823092-ad115400-ccab-11eb-8ea8-41be820fff89.png">

DoPost method is used in the servlet to process the request.

<img width="690" alt="Снимок экрана 2021-06-14 в 01 21 40" src="https://user-images.githubusercontent.com/85234616/121823585-e3040780-ccae-11eb-99c9-906fc24a63aa.png">

Result is calculated by the formula and checked for  the acceptability of the parameters. Servlet sends the response to index.jsp to the field.

<img width="690" alt="Снимок экрана 2021-06-14 в 01 00 23" src="https://user-images.githubusercontent.com/85234616/121823688-93720b80-ccaf-11eb-8a22-04b69fc38c0e.png">



## Result
<img width="392" alt="Снимок экрана 2021-06-13 в 23 31 13" src="https://user-images.githubusercontent.com/85234616/121822985-03ca5e00-ccab-11eb-8b13-ab6fe9d2ca1b.png">
<img width="403" alt="Снимок экрана 2021-06-13 в 23 31 55" src="https://user-images.githubusercontent.com/85234616/121822989-07f67b80-ccab-11eb-96cd-bf729414462b.png">
