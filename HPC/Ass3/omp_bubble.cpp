#include<iostream>
#include<omp.h>
using namespace std;
	
void simple_bubble_sort(int b[], int n) {

   for(int i=0;i< n;i++)
    {
       for(int j=0;j< n-i-1;j++) {
          if(b[j] > b[j+1]){
	   // swap(b[j], b[j+1]);
   	    int tmp = b[j];
  	    b[j] = b[j+1];
	    b[j+1] = tmp;	
	}
      }
   }

}

void omp_bubble_sort(int a[], int n) {
 
   for(int i=0;i< n;i++)
    {
       int first = i%2;

       #pragma omp parallel for defualt(none) shared(a, first)
       for(int j=first; j< n-1;j+=2) {
          if(a[j] > a[j+1]){
	    //swap(a[j], a[j+1]);	
	    int tmp =a[j];
  	    a[j] = a[j+1];
	    a[j+1] = tmp;	
	}
      }
   }

}
void merge(int c[], int imin, int imax, int jmin, int jmax) {
  int m = imin;
  int n = jmin;
  int k=0; 
  int temp[20000];
  while(m <= imax && n <= jmax){
   if(c[m] < c[n]) {

	temp[k] = c[m];
        m++;
   }else{
   	temp[k] = c[n];
	n++;
   }
   k++;
  }
  while(m <= imax){ temp[k] = c[m]; k++; m++;}
  while(n <= jmax) { temp[k] = c[n]; k++; n++;}

}
// merge sort
void simple_merge_sort(int c[], int i, int j){
 
  
  if(i < j){

    int mid = (i+j)/2;
    simple_merge_sort(c, i, mid);
    simple_merge_sort(c, mid+1, j);
    merge(c, i, mid, mid+1, j);

  }
}
// parallel merge sort
void omp_merge_sort(int c[], int i, int j){
 
  
  if(i < j){
    	 int mid = (i+j)/2;
    #pragma omp parallel section
    { 
    #pragma omp parallel section
   {  simple_merge_sort(c, i, mid); }
    #pragma omp parallel section
    {simple_merge_sort(c, mid+1, j); }}
    merge(c, i, mid, mid+1, j);

  }
}
int main() {
 cout<<"Enter the size of array"<<endl;
 int n =0;
 cin>>n;

 int a[n], b[n];
 // get the array 
 for(int i=0;i<n;i++)
 {
   a[i] = rand()%n + 1;
   b[i] = a[i];
 }

 double start = omp_get_wtime();
 simple_bubble_sort(b,n);
 double end = omp_get_wtime();
 double simple_time_taken = end - start;
 printf("Time taken by simple bubble sort %f \n",simple_time_taken);

 // sort that array using bubble sort
 
 double start_omp = omp_get_wtime();
 omp_bubble_sort(a,n);
 double end_omp = omp_get_wtime();
 double time_taken = end_omp - start_omp;
 printf("Time taken by omp bubble sort %f \n",time_taken);

 cout<<"========== MERGE SORT ==========="<<endl;
 
 int c[n], d[n];

 for(int i=0;i<n;i++)
 {
   c[i] = rand()%n + 1;
   d[i] = c[i];
 }

 start = omp_get_wtime();
 simple_merge_sort(c, 0, n-1);
  end = omp_get_wtime();
  simple_time_taken = end - start;
 printf("Time taken by simple merge sort %f \n",simple_time_taken);

 // sort that array using bubble sort
 
  start_omp = omp_get_wtime();
  omp_merge_sort(d,0, n-1);
  end_omp = omp_get_wtime();
  time_taken = end_omp - start_omp;
 printf("Time taken by omp merge sort %f \n",time_taken);

 return 0;
}
