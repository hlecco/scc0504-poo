#include <mycomplex.hpp>

int main() {
	MyComplex z(-1, 1);
	MyComplex w(-42, 80);
	MyComplex u;

	u = z.soma(w);
	u.print();

	u = z.subtracao(w);
	u.print();

	u = z.multiplicacao(w);
	u.print();

	cout << z.modulo() << endl;
		
	return 0;
}
