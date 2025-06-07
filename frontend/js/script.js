const cardContainer = document.getElementById('cardContainer');

let carros = [];

async function loadCards() {
    try {
        const response = await fetch('http://localhost:8080/api/carros/listarCarro');
        carros = await response.json();
        renderCards();
    } catch (error) {
        console.error('Error ao carregar Carros:', error);
    }
}

function renderCards() {
    cardContainer.innerHTML = '';
    carros.forEach((carro) => {
        console.log(carro);

        const card = document.createElement('div');
        card.className = 'card';

        const marca = document.createElement('h2');
        marca.textContent = carro.marca;
        card.appendChild(marca);

        const modelo = document.createElement('h3');
        modelo.textContent = carro.modelo;
        card.appendChild(modelo);

        const cor = document.createElement('p')
        cor.textContent = `Cor: ${carro.cor}`;
        card.appendChild(cor);

        const preco = document.createElement('p');
        preco.textContent = `Preço: ${carro.preco}`;
        card.appendChild(preco);

        const quilometragem = document.createElement('p');
        quilometragem.textContent = `Quilometragem: ${carro.quilometragem}`;
        card.appendChild(quilometragem);

        const disponibilidade = document.createElement('p');
        disponibilidade.textContent = `Disponibilidade: ${carro.disponibilidade ? 'Disponível' : 'Indisponível'}`;
        card.appendChild(disponibilidade);

        const editButton = document.createElement('button');
        editButton.textContent = 'Editar';
        editButton.onclick = () => {
            localStorage.setItem('carroParaEditar', JSON.stringify(carro));
            window.location.href = 'editarVeiculo.html';
        };


        card.appendChild(editButton);

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Deletar';
        deleteButton.onclick = () => deleteCar(carro.id);
        card.appendChild(deleteButton);

        cardContainer.appendChild(card);
    });
}

async function addCars() {
    const modelo = document.getElementById('modeloInput').value.trim();
    const marca = document.getElementById('marcaInput').value.trim();
    const cor = document.getElementById('corInput').value.trim();
    const preco = parseFloat(document.getElementById('precoInput').value);
    const quilometragem = parseInt(document.getElementById('quilometragemInput').value);
    const disponibilidade = document.getElementById('opcoes').value === '1'; // true se 1, false se 2

    if (modelo && marca && cor && !isNaN(preco) && !isNaN(quilometragem)) {
        const newCar = {
            modelo,
            marca,
            cor,
            preco,
            quilometragem,
            disponibilidade,
        };

        console.log("Enviando carro para cadastro:", JSON.stringify(newCar));

        try {
            const response = await fetch('http://localhost:8080/api/cars/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newCar),
            });

            if (response.ok) {
                loadCards();
                clearForm();
            } else {
                const errorText = await response.text();
                console.error('Erro ao cadastrar Carro:', errorText);
                alert('Erro ao cadastrar: ' + errorText);
            }
        } catch (error) {
            console.error('Erro ao cadastrar Carro:', error);
            alert('Erro ao cadastrar: ' + error.message);
        }
    } else {
        alert('Por favor, preencha todos os campos corretamente.');
    }
}


function createEditForm(carro, card) {
    card.innerHTML = '';

    const modeloInput = document.createElement('input');
    modeloInput.type = 'text';
    modeloInput.value = carro.modelo;

    const marcaInput = document.createElement('input');
    marcaInput.type = 'text';
    marcaInput.value = carro.marca;

    const corInput = document.createElement('input');
    corInput.type = 'text';
    corInput.value = carro.cor;

    const precoInput = document.createElement('input');
    precoInput.type = 'number';
    precoInput.value = carro.preco;

    const quilometragemInput = document.createElement('input');
    quilometragemInput.type = 'number';
    quilometragemInput.value = carro.quilometragem;

    const saveButton = document.createElement('button');
    saveButton.textContent = 'Salvar';
    saveButton.onclick = async () => {
        const updatedCar = {
            modelo: modeloInput.value,
            marca: marcaInput.value,
            cor: corInput.value,
            preco: precoInput.value,
            quilometragem: quilometragemInput.value,
        };

        try {
            const response = await fetch(`http://localhost:8080/api/cars/update/${carToEdit.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedCar),
            });

            if (response.ok) {
                
                Object.assign(carro, updatedCar);
                renderCards();
            } else {
                console.error('Erro ao atualizar carro:', await response.text());
            }
        } catch (error) {
            console.error('Erro ao atualizar carro:', error);
        }
    };


    const cancelButton = document.createElement('button');
    cancelButton.textContent = 'Cancelar';
    cancelButton.onclick = () => {
        renderCards(); 
    };


    card.appendChild(modeloInput);
    card.appendChild(marcaInput);
    card.appendChild(corInput);
    card.appendChild(precoInput);
    card.appendChild(quilometragemInput);
    card.appendChild(saveButton);
    card.appendChild(cancelButton);
}



async function deleteCar(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/cars/delete/${id}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            loadCards();
        } else {
            console.error('Erro ao deletar carro:', await response.text());
        }
    } catch (error) {
        console.error('Erro ao deletar carro:', error);
    }
}

function clearForm() {
    document.getElementById('modeloInput').value = '';
    document.getElementById('marcaInput').value = '';
    document.getElementById('corInput').value = '';
    document.getElementById('precoInput').value = '';
    document.getElementById('quilometragemInput').value = '';
}

loadCards();