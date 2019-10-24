#include<iostream>
#include<chrono>
#include<mpi.h>
#include<time.h>

using namespace std::chrono;
using namespace std;

void binarySearch(int a[], int start, int end, int key, int rank) {
 
 
 while(start <= end) {
    int mid = (start+end)/2;  
   if(a[mid] == key) {
     cout<<"\nThe element is found by the process "<<rank + 1<<endl;
     return;
   }
   else if(a[mid] > key) {
     end = mid -1;
   }
   else{
     start = mid+1;
   }
 }
 cout<<"\nThe element is not found by the process "<<rank+1<<endl; 
}
int main(int argc, char **argv) {

 int n, key;
 n = 8000;
 key = 2500;
 int a[n];
 for(int i=0;i<n;i++) { a[i] = i+1; }

 int rank, blocksize;
 

 MPI_Init(&argc,&argv);
 MPI_Comm_rank(MPI_COMM_WORLD,&rank);
 MPI_Comm_size(MPI_COMM_WORLD,&blocksize);
 blocksize = n/4;

 double start = MPI_Wtime();
 binarySearch(a, rank*blocksize, (rank+1)*blocksize -1, key, rank);
 double end = MPI_Wtime();
 cout<<"\nTime taken by the process "<<rank+1<<" is "<<(end-start)*1000<<" secs"<<endl;
 MPI_Finalize();
 return 0;

}
