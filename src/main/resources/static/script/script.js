////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Global constant Declaration section                                                                                //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
const messageBoxes = document.querySelectorAll(".message");

//specific declaration of DOMs from candidate.html page
const searchAnchor = document.querySelector("#search-link");
const sortAnchor = document.querySelector("#sort-link");
const searchInput = document.querySelector("#search-input");
const sortDropDown = document.querySelector("#sort-category");
const sortOrderDropDown = document.querySelector("#sort-order-category");

//specific declaration of DOMs from settings.html page
const historicalBrowseButton = document.querySelector("#browse1");
const currentBrowseButton = document.querySelector("#browse2");
const historicalStyleSheetName = document.querySelector("#historical-spread-sheet-name");
const currentStyleSheetName = document.querySelector("#current-spread-sheet-name");
const historicalFileInput = document.querySelector("#historical-spread-sheet-file");
const currentFileInput = document.querySelector("#current-spread-sheet-file");

//specific declaration of DOMs from admin.html page
const adminSubmitButtons = document.querySelectorAll(".action-btn");
const adminActionSelect = document.querySelectorAll(".action-select");

//specific declaration of DOMs for mobile responsiveness
const toggleIcon = document.querySelector("#summary-display-toggle");
const leftContent = document.querySelector("#main-content-left");
const asideContent = document.querySelector("#aside");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of Global Constant Declaration section                                                                         //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//script for hiding error and success messages across pages


messageBoxes.forEach(box => box.addEventListener("click", (_e) => box.style.display = "none"));

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Scripts for candidates.html                                                                                         //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//script for preparing a GET request to search for a candidate or candidates
const prepareSearchRequestOnClick = () => {
    if (searchInput.value && sortDropDown.value && sortOrderDropDown.value) {
        let term = sortDropDown.value;
        let searchValue = searchInput.value;
        let sortOrderKey = "order";
        let sortOrderValue = sortOrderDropDown.value;
        searchAnchor.href = `q?${term}=${searchValue}&${sortOrderKey}=${sortOrderValue}`;
    }

};
//script for toggling between ascending and descending order
const toggleSortOrderOnclick = () => {
    let sortOrders = ["ACS", "DESC"];
    const sortOrderIcon = document.querySelector("#sort-order-icon");

    sortOrderIcon.dataset.order = sortOrderIcon.dataset.order === sortOrders[0] ? sortOrders[1] : sortOrders[0];

};
//script for preparing a GET request to sort candidates
const prepareSortRequestOnclick = () => {
    if (sortDropDown.value && sortOrderDropDown.value) {
        let sortValue = sortDropDown.value;
        let sortOrderValue = sortOrderDropDown.value;
        let sortOrderKey = "order";
        sortAnchor.href = `sortBy?attr=${sortValue}&${sortOrderKey}=${sortOrderValue}`;
    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of scripts for candidates.html                                                                                 //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// scripts for settings.html                                                                                          //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//script for browsing historical spreadsheet data for uploads to server
if (historicalBrowseButton) {
    historicalBrowseButton.addEventListener("click", evt => {
        historicalFileInput.click();
        historicalFileInput.onchange = () => historicalStyleSheetName.textContent = historicalFileInput.files[0].name;
        evt.preventDefault();
    });

}
//script for browsing current spreadsheet data for uploads to server
if (currentBrowseButton) {
    currentBrowseButton.addEventListener("click", evt => {
        currentFileInput.click();
        currentFileInput.onchange = () => currentStyleSheetName.textContent = currentFileInput.files[0].name;
        evt.preventDefault();
    });

}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//End of scripts settings.html                                                                                        //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Scripts for admin.html                                                                                             //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//script for animating the submit buttons in the admin page
if (adminActionSelect) {
    adminActionSelect.forEach(box => {
        box.addEventListener("change", _evt => {
            let index = [...adminActionSelect].indexOf(box);
            let actionBtn = [...adminSubmitButtons][index]
            let selectValue = box.value;
            actionBtn.style.background = (selectValue === "delete") ? "#f00" : "#214ea3";
        });
    });

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// End of script for admin.html                                                                                       //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if (toggleIcon) {
    toggleIcon.addEventListener("click", _evt => {
        if (leftContent.style.display === 'block') {
            toggleIcon.setAttribute("src", "/images/close.svg");
            leftContent.style.display = 'none';
            asideContent.style.display = 'block';
            asideContent.classList.add("sx-mobile-aside");
        } else {
            leftContent.style.display = 'block';
            asideContent.style.display = 'none';
            asideContent.classList.remove("sx-mobile-aside");
            toggleIcon.setAttribute("src", "/images/hamburger.svg");
        }
    });
}

const match601To1025Px = (media) => {
    if (media.matches) {
        toggleIcon.setAttribute("src", "/images/hamburger.svg");
        leftContent.style.display = 'block';
        asideContent.style.display = 'block';
        toggleIcon.style.display = 'none';
    }
};

const match0To600Px = (media) =>{
    if (media.matches) {
        toggleIcon.style.display = 'block';
        if (asideContent.classList.contains("sx-mobile-aside")) {
            asideContent.classList.remove("sx-mobile-aside");
        }
        asideContent.style.display = 'none';
    }
};
window.matchMedia("(min-width: 601px) and (max-width: 1025px)")
    .addEventListener('change',match601To1025Px);
window.matchMedia("(max-width: 600px)").addEventListener('change', match0To600Px);
