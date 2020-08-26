# Make sure to import the necessary packages and modules
import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np

# leo de vaOut la salida de los diferentes va
filename1 = "../OutFiles/vaVsTime.txt"

# en cada fila de t tengo los valores en ese t
t = np.loadtxt(filename1, unpack=True)
X = list(range(0, len(t)))
Y = list(range(0, len(t)))
E = list(range(0, len(t)))

for i in range(0, len(t)):
    X[i] = np.mean(t[i])
    E[i] = np.std(t[i])

x_coordinates = [0, len(t)]
y_coordinates = [1, 1]

plt.plot(x_coordinates, y_coordinates, linestyle='--',linewidth=0.3)
plt.errorbar(Y, X, E, linestyle='-', marker='.')
plt.title("Va vs Tiempo")
plt.xlabel('Tiempo [segundos]')
plt.ylabel('Va')
plt.show()
