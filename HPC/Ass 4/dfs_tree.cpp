#include<iostream>
#include<time.h>
#include<omp.h>

using namespace std;
class Node {
 public:
  int data;
  Node *left;
  Node *right;
  Node(int val) { 
   data = val;
   left = NULL;
   right = NULL;
  }
};
void simple_preorder_traversal(Node* node) {
  if(node == NULL) return;
  cout<<node->data<<" ";
  simple_preorder_traversal(node->left);
  simple_preorder_traversal(node->right);
}

void omp_preorder_traversal(Node* node){
  if(node == NULL) return;
  cout<<node->data<<" ";
  #pragma omp parallel section
  {
    simple_preorder_traversal(node->left);
  }
  #pragma omp parallel section 
  {
    simple_preorder_traversal(node->right);
  }
}
int main() {
 Node* node = new Node(1);
 node->left = new Node(2);
 node->right = new Node(3);
 node->left->left = new Node(4);
 node->left->right = new Node(5);
 node->right->left = new Node(6);
 node->right->right = new Node(7);

 double start = omp_get_wtime();
 simple_preorder_traversal(node);
 double end = omp_get_wtime();

 cout<<"\nTime taken by simple dfs is "<<(end-start)*1000<<" secs"<<endl;

  start = omp_get_wtime();
  omp_preorder_traversal(node);
  end = omp_get_wtime();
  cout<<"\nTime taken by omp dfs is "<<(end-start)*1000<<" secs"<<endl;
 
return 0;
}

