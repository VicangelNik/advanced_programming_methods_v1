Διατύπωση ζητουμένου
Το ζητούμενο της εργασίας είναι να αναπτυχθεί ένα πρόγραμμα σε γλώσσα Java, το οποίο να αξιοποιεί
τεχνικές Reflection για να απαντήσει στα ακόλουθα ερωτήματα:
1. Ποιοι είναι οι top-N τύποι του προγράμματος που έχουν τα περισσότερα:
   a. Δηλωμένα (declared) πεδία.
   b. Συνολικά (declared και inherited) πεδία.
2. Ομοίως, τις περισσότερες:
   a. Δηλωμένες μεθόδους.
   b. Συνολικές μεθόδους.
3. Ομοίως, τους περισσότερους υπο-τύπους.
4. Ομοίως, τους περισσότερους υπερ-τύπους.
   Προθεσμία εργασίας
   Η εργασία είναι ατομική και η υποβολή της θα πρέπει να γίνει το αργότερο ως τις 23:59 την Κυριακή
   6/11, μέσω της αντίστοιχης ενότητας του eclass (όλον τον πηγαίο σας κώδικα, αποτελούμενο από ένα ή
   περισσότερα αρχεία .java, συμπιεσμένο σε ένα αρχείο .zip).
   Κοινές παραδοχές & προδιαγραφές
5. Θα χρησιμοποιήσετε Java 11.
6. Το όνομα της main κλάσης σας (αυτής που περιέχει την main μέθοδο) θα πρέπει να είναι Main.
7. Το πρόγραμμά σας θα πρέπει να λαμβάνει υποχρεωτικά 3 παραμέτρους (με αυτήν τη σειρά)1
   :
   a. <input-file>: το μονοπάτι για το αρχείο κειμένου που περιέχει τα ονόματα των τύπων
   που θα θεωρήσετε ότι αποτελούν το πρόγραμμά σας (βλ. παρακάτω). Το αρχείο αυτό θα
   έχει ένα όνομα τύπου ανά γραμμή.
   1
   Το πρόγραμμά σας, δηλαδή, θα «τρέχει» με την ακόλουθη εντολή: java -cp <classpath> Main <input-file> <outputfile> <value-of-N>
   b. <output-file>: το μονοπάτι για το αρχείο που θα δημιουργήσει το πρόγραμμά σας και
   στο οποίο θα αποθηκεύσει τα ζητούμενα, όπως φαίνεται παρακάτω στο σχετικό
   υπόδειγμα.
   c. <value-of-N>: την τιμή του N (π.χ. 10, ώστε να υπολογιστούν οι top 10 τύποι για κάθε
   ζητούμενο).
8. Θα πρέπει να θεωρήσετε ότι το πρόγραμμά σας (ανά επίκληση) αποτελείται από τους τύπους
   που περιέχονται στο αρχείο εισόδου και τους τύπους που περιέχονται στην «standard»
   βιβλιοθήκη της Java 11 (τους τελευταίους, να θεωρήσετε ότι μπορείτε να τους διακρίνετε από το
   όνομα του module τους, το οποίο θα ξεκινά είτε με «java.», είτε με «jdk.»). Αποτελεί ευθύνη του
   χρήστη που καλεί το πρόγραμμά σας να διαμορφώσει το classpath με τέτοιο τρόπο, ώστε οι τύποι
   που παρέχονται στο αρχείο εισόδου να μπορούν να εντοπιστούν από το πρόγραμμά σας
   δυναμικά (κατά τον χρόνο εκτέλεσης), μέσω του Reflection API και συγκεκριμένα, μέσω της
   επίκλησης της static μεθόδου forName(String) της κλάσης java.lang.Class (ουσιαστικά καλείτε
   αυτήν την μέθοδο για κάθε γραμμή του αρχείου εισόδου, για να «φορτώσετε» δυναμικά τον
   αντίστοιχο τύπο).
9. Το αρχείο εξόδου θα πρέπει να περιέχει 6 γραμμές, μία για κάθε ζητούμενο. Κάθε γραμμή θα
   πρέπει να βασίζεται στο ακόλουθο υπόδειγμα:
   1a|1b|2a|2b|3|4: comma-separated list of top-N fully qualified type names
   Για παράδειγμα:
   1a: x.y.Class1, x.y.Class4, …
   1b: x.y.Class1, x.y.Class2, …
   2a: x.y.Class3, x.y.Class4, …
   2b: x.y.Class5, x.y.Interface3, …
   3: x.y.Class1, x.y.Interface2, …
   4: x.y.Class1, x.y.Class2, …*/