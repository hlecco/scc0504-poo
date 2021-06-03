#include <mycomplex.hpp>

using namespace std;

int main() {
	MyComplex z(-1, 1);
	MyComplex w(-42, 80);
	MyComplex u;

	u = z.sum(w);
	u.print();

	u = z.sub(w);
	u.print();

	u = z.mul(w);
	u.print();

	cout << z.abs() << endl;
		
	return 0;
}
