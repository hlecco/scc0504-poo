#include <mycomplex.hpp>

using namespace std;

int main() {
	int n;
	vector<MyComplex *> vec;
	MyComplex *sum = new MyComplex;

	cout << "Type the size of the vector: ";
	cin >> n;
	srand(time(NULL));
	
	for (int i = 0; i < n; i++) {
		double real = rand() % 10;
		double imag = rand() % 10;
		MyComplex *element = new MyComplex(real, imag);
		vec.push_back(element);
		cout << "Vector " << i << ": ";
		element->print();
	}

	vector<MyComplex *>::iterator it;
	for (it = vec.begin(); it != vec.end(); it++) {
		*sum = **it + *sum;
	}

	sum->print();
	
	for (it = vec.begin(); it != vec.end(); it++) {
		delete *it;
	}
	
	delete sum;

	return 0;
}
