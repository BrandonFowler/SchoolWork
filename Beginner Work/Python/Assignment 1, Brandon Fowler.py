#Brandon Fowler, Assignment 1


#Imports

import math


#Definitions
    
    
def circleCircumference(p_r1):
    ComputeCircleCircumference =((p_r1*2)*math.pi)
    return ComputeCircleCircumference
    

def circleArea(p_r2):
    ComputeCircleArea =(p_r2**2*math.pi)
    return ComputeCircleArea

def sphereSurfaceArea(p_r3):
    ComputeSphereArea =((4*math.pi)*p_r3**2)
    return ComputeSphereArea

def sphereVolume(p_r4):
    ComputeSphereVolume =((4/3*math.pi)*p_r4**3)
    return ComputeSphereVolume


#Main



radius = eval(input("Enter Radius"))

CircleCircumferenceResult = circleCircumference(radius)

CircleAreaResult = circleArea(radius)

SphereSurfaceAreaResult = sphereSurfaceArea(radius)

SphereVolumeResult = sphereVolume(radius)



print("Circle Circumference:",CircleCircumferenceResult)

print("Circle Area:",CircleAreaResult)

print("Sphere Surface Area:",SphereSurfaceAreaResult)

print("Sphere Volume:",SphereVolumeResult)


