<!DOCTYPE html>
<html>
<head>
    <title>default</title>
</head>
<body style="background-color:moccasin">
<form action="/" method="post" commandName="input" enctype="multipart/form-data" >
    <table>
        <thead>
        <body align = "left">
            <h1>
                <label>Similarity Join</label>
            </h1>

            <h3>
                <label>library for point extraction</label>
            </h3>

            <p>
                <label>OpenSurf:</label>
                <input type="radio" id="openSurf" name="libType" value="SURF" checked>
                <label>OpenCv:</label>
                <input type="radio" id="openCv" name="libType" value="SIFT" >

            </p>

            <h3>
                <label>Values</label>
            </h3>

            <p>
                <label>knn:</label>
                <input type="radio" id="res" name="resType" value="knn" checked >
                <label>max:</label>
                <input type="radio" id="res" name="resType" value="max" >
                <label>min:</label>
                <input type="radio" id="res" name="resType" value="min" >
                <label>value</label>
                <input type="number" id="simPer" name="resValue"   min="0" max="100" value="5" required>
            </p>


            <p>
                <label>number of clusters:</label>
                <input type="number" id="nofClusters" name="nofClusters" min="0" max="1000" value="30" required>
                <label>limit of kMeans iterations:</label>
                <input type="number" id="nofIter" name="nofIter" min="1" max="100000" value="500" required>

            </p>

            <p>
            <h3>
                <label>Functions for comparison</label>
            </h3>
            </body>

            <p>
            <h4><label>SQFD:</label></h4>
                <input type="radio" id="radTypeSQFD" name="compType" value="SQFD" checked>
            </p>

            <p>
                <label>minus:</label>
                <input type="radio" id="funcTypeMinus" name="funType" value="minus" checked >
                <label>Guassian:</label>
                <input type="radio" id="funcTypeEuc" name="funType" value="gaussian" >
                <label>heuristic:</label>
                <input type="radio" id="funcTypeHeuristic" name="funType" value="heuristic" >
                <label>alpha constant:</label>
                <input type="number" id="alphaConst" name="alphaConst" step="0.1" min="0.1" max="100" value="1" required>

            </p>

            <p>
            <h4><label>Euclid:</label></h4>
                <input type="radio" id="radTypeEuclid" name="compType" value="Euclid">
            </p>

            <p>
                <label>distance percentage limit:</label>
                <input type="number" id="disLim" name="disLimPer" step="0.01" min="0" max="1" value="0.05" required>
            </p>

            <h3>
                <label>Files</label>
            </h3>

            <p>
                <label>images1:</label>
                <input type="file" name="images1" accept=".zip" required/>
            </p>

            <p>
                <label>images2:</label>
                <input type="file" name="images2" accept=".zip" required/>
            </p>



            <p>
                <button id = "btnSubmit" type='submit' style="height:50px;font-size:30px;"><i>compare</i></button>
            </p>
        </capation>
        </thead>
    </table>
</form>
</body>
</html>